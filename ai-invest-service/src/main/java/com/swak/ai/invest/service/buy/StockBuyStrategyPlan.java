package com.swak.ai.invest.service.buy;

/**
 * 股票加仓计划策略
 *
 * @author: ljq
 * @date: 2024/11/22
 */
public interface StockBuyStrategyPlan {

    StockBuyPlanResult buyPlan();

    /**
     * 计划名称
     */
    StockBuyPlanName name();
}
