package com.swak.ai.invest.service.buy;

import cn.hutool.core.util.NumberUtil;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.entity.InvestConstants;
import com.swak.ai.invest.service.data.StockQuote;
import com.swak.ai.invest.service.data.StockQuoteDataService;
import com.swak.lib.common.number.BigNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 左侧固定比例加仓策略
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StockLeftFixedBuyStrategy implements StockBuyStrategyPlan {


    private final AccountStockPositionMapper accountStockPositionMapper;

    private final StockQuoteDataService stockQuoteDataService;

    @Override
    public StockBuyPlanResult buyPlan(StockBuyContext context) {

        StockBuyPlanResult planResult = new StockBuyPlanResult();
        // 没跌1%的仓位 左侧加仓计划
        AccountStockPositionDo stockPosition = accountStockPositionMapper.getBy(context.getAccountId(), context.getTsCode());
        if (Objects.isNull(stockPosition)) {
            // 初始化持仓账户
            stockPosition.setAccountId(context.getAccountId());
            stockPosition.setTsCode(context.getTsCode());
            stockPosition.setQuantity(0);
            stockPosition.setPlanAmount(NumberUtil.toBigDecimal(InvestConstants.defaultPlanAmount));
            stockPosition.setCreateTime(new Date());
            accountStockPositionMapper.insert(stockPosition);
        }

        StockQuote stockQuote = stockQuoteDataService.getStockQuote(context.getTsCode());

        // 预计最低可能的股价
        BigDecimal planMinPrice = getPlanMinPrice(context.getTsCode());
        // 当前股价
        BigNumber currentPrice = BigNumber.of(stockQuote.getCurrentPrice());
        // 计算加仓次数
        int maxBuyCnt = currentPrice.sub(planMinPrice).divScale2(currentPrice.mul(context.getFallRate())).intValue();

        int minShares = 100;

        BigNumber fallRate = BigNumber.of(context.getFallRate());
        BigNumber totalCost = BigNumber.ZERO;
        BigDecimal currentPe = stockQuote.getPe();
        int totalShares = 0;
        for (int i = 1; i <= maxBuyCnt; i++) {

            StockBuyPlanUnit planUnit = new StockBuyPlanUnit();

            BigNumber linePrice = currentPrice;
            BigDecimal linePe = currentPe;

            // 第一次的时候就按当前股价买计算
            if (i > 1) {
                fallRate = fallRate.add(context.getFallRate());
                // 计算当前PE
                StockCalculator calculator = new StockCalculator(currentPe, currentPrice, fallRate);
                calculator = calculator.calculator();
                // 新的pe
                linePrice = calculator.getPrice();
                linePe = calculator.getPe();
            }

            // 计算加前面的总成本
            totalCost = totalCost.add(linePrice.mul(minShares)).round2HalfUp();
            // 总股数
            totalShares = totalShares + minShares;

            // 计算当前持仓平均成本
            BigNumber averageCost = totalCost.divScale2(totalShares);

            // 计算当前持仓总亏损
            BigNumber currentLoss = currentPrice.sub(averageCost).mul(totalShares).round2HalfUp();

            planUnit.setAveragePrice(averageCost.getValue());
            planUnit.setPe(linePe);
            planUnit.setBuyPrice(linePrice.getValue());
            planUnit.setTotalLossAmount(currentLoss.getValue());
            planUnit.setTotalBuyAmount(totalCost.getValue());
            planUnit.setBuyShares(minShares);
            planUnit.setFallRate(fallRate.getValue());
            planResult.getBuyPlanUnits().add(planUnit);
        }

        return planResult;
    }

    private BigDecimal getPlanMinPrice(String tsCode) {

        return NumberUtil.toBigDecimal(68);
    }

    @Override
    public StockBuyPlanName name() {
        return StockBuyPlanName.leftBuy;
    }
}
