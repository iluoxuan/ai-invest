package com.swak.ai.invest.service.buy;

import com.swak.ai.inverst.common.entity.stock.StockSearchReq;
import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.ai.invest.entity.account.StockBaseRes;
import com.swak.ai.invest.entity.buy.StockBuyContext;
import com.swak.ai.invest.entity.buy.StockBuyPlanReq;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;
import com.swak.ai.invest.service.covert.StockCovertService;
import com.swak.lib.client.exception.SwakBizException;
import com.swak.lib.common.tools.BeanTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.swak.ai.invest.entity.InvestConstants.searchByStock;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@RequiredArgsConstructor
@Service
public class StockBuyPlanManager {

    private final List<StockBuyStrategyPlan> stockBuyStrategyPlanList;
    private final StockMapper stockMapper;
    private final StockCovertService stockCovertService;

    public StockBuyPlanResult buyPlan(StockBuyPlanReq req) {


        StockBuyContext context = BeanTools.copy(req, StockBuyContext.class);
        context.setAccountId(UserContext.getInstance().getAccountId());

        for (StockBuyStrategyPlan plan : stockBuyStrategyPlanList) {

            if (plan.name().equals(req.getPlanName())) {
                return plan.buyPlan(context);
            }
        }

        throw SwakBizException.argumentError("未找到加仓计划");
    }

    public List<StockBaseRes> search(StockSearchReq req) {

        req.setLimit(searchByStock);
        List<StockDo> stockList = stockMapper.search(req);

        return BeanTools.copyList(stockList, stockDo -> stockCovertService.covert(stockDo));
    }

    public List<StockBaseRes> aiRecommend() {
        return null;
    }
}
