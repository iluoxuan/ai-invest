package com.swak.ai.invest.service.buy;

import cn.hutool.core.util.NumberUtil;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.entity.InvestConstants;
import com.swak.ai.invest.service.data.StockQuote;
import com.swak.ai.invest.service.data.StockQuoteDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        // 计算加仓次数
        BigDecimal maxBuyCnt = NumberUtil.sub(stockQuote.getCurrentPrice(), planMinPrice)
                .divide(stockQuote.getCurrentPrice().multiply(context.getFallRate()));

        BigDecimal minShares = NumberUtil.toBigDecimal(100);

        // 初始化变量
        BigDecimal currentPrice = stockQuote.getCurrentPrice();
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal totalShares = BigDecimal.ZERO;
        for (int i = 1; i <= maxBuyCnt.intValue(); i++) {

            StockBuyPlanUnit planUnit = new StockBuyPlanUnit();

            // 计算当前加仓后的总成本和总持股数
            totalCost = totalCost.add(currentPrice.multiply(minShares)).setScale(2, RoundingMode.HALF_UP);
            totalShares = totalShares.add(minShares);

            // 计算当前持仓平均成本
            BigDecimal averageCost = totalCost.divide(totalShares, 2, RoundingMode.HALF_UP);

            // 计算当前持仓亏损
            BigDecimal currentLoss = currentPrice.subtract(averageCost)
                    .multiply(totalShares).setScale(2, RoundingMode.HALF_UP);

            // 计算当前PE值
            BigDecimal peValue = currentPrice.multiply(totalShares).divide(totalCost, 2, RoundingMode.HALF_UP);

            // 更新当前股价
            if (i < maxBuyCnt.intValue()) {
                currentPrice = currentPrice.multiply(BigDecimal.ONE.subtract(context.getFallRate()))
                        .setScale(2, RoundingMode.HALF_UP);
            }

            planUnit.setPe(peValue);
            planUnit.setBuyPrice(currentPrice);
            planUnit.setTotalLossAmount(currentLoss);
            planUnit.setTotalBuyAmount(totalCost);
            planUnit.setBuyShares(minShares.intValue());
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
