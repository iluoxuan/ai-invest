package com.swak.ai.invest.service.buy;

import com.swak.lib.client.exception.SwakBizException;
import com.swak.lib.common.tools.BeanTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@RequiredArgsConstructor
@Service
public class StockBuyPlanManager {

    private List<StockBuyStrategyPlan> stockBuyStrategyPlanList;

    public StockBuyPlanResult buyPlan(StockBuyPlanReq req) {

        for (StockBuyStrategyPlan plan : stockBuyStrategyPlanList) {

            if (plan.name().equals(req.getPlanName())) {

                StockBuyContext context = BeanTools.copy(req, StockBuyContext.class);
                return plan.buyPlan(context);
            }
        }

        throw SwakBizException.argumentError("未找到加仓计划");
    }

}
