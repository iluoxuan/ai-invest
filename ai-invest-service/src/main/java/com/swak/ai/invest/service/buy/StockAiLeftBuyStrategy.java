package com.swak.ai.invest.service.buy;

import com.swak.ai.invest.entity.buy.StockBuyContext;
import com.swak.ai.invest.entity.buy.StockBuyPlanName;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ai 左侧加仓优化
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@RequiredArgsConstructor
@Service
public class StockAiLeftBuyStrategy implements StockBuyStrategyPlan {
    @Override
    public StockBuyPlanResult buyPlan(StockBuyContext context) {


        // 将对于的 相关参数定义好 传给ai
        // 让ai返回json格式的数据 解析

        return null;
    }

    @Override
    public StockBuyPlanName name() {
        return StockBuyPlanName.aiLeftBuy;
    }
}
