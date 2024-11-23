package com.swak.ai.invest.service.buy;

import com.swak.ai.invest.entity.buy.StockBuyContext;
import com.swak.ai.invest.entity.buy.StockBuyPlanName;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;

/**
 * 股票加仓计划策略
 *
 * @author: ljq
 * @date: 2024/11/22
 */
public interface StockBuyStrategyPlan {

    StockBuyPlanResult buyPlan(StockBuyContext context);

    /**
     * 计划名称
     */
    StockBuyPlanName name();
}
