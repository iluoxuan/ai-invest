package com.swak.ai.invest.service.buy;

import org.springframework.stereotype.Service;

/**
 * 左侧加仓策略
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@Service
public class StockLeftBuyStrategy implements StockBuyStrategyPlan {


    @Override
    public StockBuyPlanResult buyPlan() {

        // 没跌1%的仓位 左侧加仓计划



        return null;
    }

    @Override
    public StockBuyPlanName name() {
        return StockBuyPlanName.leftBuy;
    }
}
