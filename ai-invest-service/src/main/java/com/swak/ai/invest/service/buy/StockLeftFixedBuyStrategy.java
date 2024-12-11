package com.swak.ai.invest.service.buy;

import cn.hutool.core.lang.Assert;
import com.swak.ai.inverst.common.entity.stock.StockQuote;
import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.dao.mapper.UserInvestAccountMapper;
import com.swak.ai.invest.data.stock.quote.DefaultStockQuoteSpider;
import com.swak.ai.invest.entity.buy.StockBuyContext;
import com.swak.ai.invest.entity.buy.StockBuyPlanName;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;
import com.swak.ai.invest.entity.buy.StockBuyPlanUnit;
import com.swak.lib.common.number.BigNumber;
import com.swak.lib.common.tools.AssertTools;
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
    private final DefaultStockQuoteSpider defaultStockQuoteSpider;
    private final UserInvestAccountMapper userInvestAccountMapper;
    private final StockBuyAmountService stockBuyAmountService;

    @Override
    public StockBuyPlanResult buyPlan(StockBuyContext context) {

        StockBuyPlanResult planResult = new StockBuyPlanResult();

        UserInvestAccountDo account = userInvestAccountMapper.getByUserId(UserContext.userId());
        AssertTools.notNull(account, "帐户还没初始化");
        context.setAccountId(account.getAccountId());

        // 没跌1%的仓位 左侧加仓计划
        AccountStockPositionDo stockPosition = accountStockPositionMapper.getBy(context.getAccountId(), context.getTsCode());
        if (Objects.isNull(stockPosition)) {

            stockPosition = new AccountStockPositionDo();
            // 初始化持仓账户
            stockPosition.setAccountId(account.getAccountId());
            stockPosition.setTsCode(context.getTsCode());
            stockPosition.setQuantity(0);
            stockPosition.setPlanAmount(stockBuyAmountService.buyAmountRule(account.getAvailableAmount()));
            stockPosition.setAvailableAmount(account.getAvailableAmount());
            stockPosition.setCreateTime(new Date());
            accountStockPositionMapper.insert(stockPosition);
        }
        planResult.setPosition(stockPosition);

        StockQuote stockQuote = defaultStockQuoteSpider.spider(context.getTsCode());
        Assert.isFalse(stockQuote.isEmpty(), "实时股票信息获取失败");

        // 预计最低可能的股价
        BigDecimal planMinPrice = getPlanMinPrice(stockQuote);
        // 当前股价
        BigNumber currentPrice = BigNumber.of(stockQuote.getCurrentPrice());
        // 计算加仓次数
        int maxBuyCnt = currentPrice.sub(planMinPrice).divScale2(currentPrice.mul(context.getFallRate())).intValue();

        int minShares = 100;
        BigNumber fallRate = BigNumber.ZERO;
        BigNumber totalCost = BigNumber.ZERO;
        int totalShares = 0;
        planResult.setBuyCnt(maxBuyCnt);

        for (int i = 1; i <= maxBuyCnt; i++) {

            StockBuyPlanUnit planUnit = new StockBuyPlanUnit();
            StockChange stockChange = new StockChange(stockQuote.getPe(), currentPrice, fallRate);
            planUnit.setFallRate(fallRate.getValue());

            fallRate = fallRate.add(context.getFallRate());

            // 计算加前面的总成本
            totalCost = totalCost.add(stockChange.getPrice().mul(minShares)).round2HalfUp();
            // 总股数
            totalShares = totalShares + minShares;
            // 计算当前持仓平均成本
            BigNumber averageCost = totalCost.divScale2(totalShares);
            // 计算当前持仓总亏损
            BigNumber currentLoss = stockChange.getPrice().sub(averageCost).mul(totalShares).round2HalfUp();

            planUnit.setBuyAvgPrice(averageCost.getValue());
            planUnit.setPe(stockChange.getPe());
            planUnit.setCurrentPrice(stockChange.getPrice().getValue());
            planUnit.setTotalLoss(currentLoss.getValue());
            planUnit.setTotalBuyAmount(totalCost.getValue());
            planUnit.setShares(minShares);
            planResult.getBuyPlanUnits().add(planUnit);
        }
        return planResult;
    }

    private BigDecimal getPlanMinPrice(StockQuote stockQuote) {

        // TODO 计算最低加仓位置 默认取52周最低1

        return stockQuote.getLow52w();
    }

    @Override
    public StockBuyPlanName name() {
        return StockBuyPlanName.leftBuy;
    }
}
