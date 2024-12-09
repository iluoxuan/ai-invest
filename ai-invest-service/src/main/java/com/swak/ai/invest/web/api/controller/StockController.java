package com.swak.ai.invest.web.api.controller;

import com.swak.ai.inverst.common.entity.stock.StockSearchReq;
import com.swak.ai.invest.entity.account.StockBaseRes;
import com.swak.ai.invest.entity.buy.StockBuyPlanReq;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;
import com.swak.ai.invest.service.buy.StockBuyPlanManager;
import com.swak.lib.client.entity.ApiRes;
import com.swak.lib.common.annotion.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@LogTrace
@Api(tags = "股票仓位")
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockBuyPlanManager stockBuyPlanManager;

    @ApiOperation("左侧加仓")
    @PostMapping("/leftBuy")
    public ApiRes<StockBuyPlanResult> leftBuy(@Valid @RequestBody StockBuyPlanReq req) {
        return ApiRes.success(stockBuyPlanManager.buyPlan(req));
    }

    @ApiOperation("搜索加仓")
    @PostMapping("/search")
    public ApiRes<List<StockBaseRes>> search(@RequestBody StockSearchReq req) {
        return ApiRes.success(stockBuyPlanManager.search(req));
    }

    @ApiOperation("搜索加仓")
    @PostMapping("/ai/recommend")
    public ApiRes<List<StockBaseRes>> aiRecommend() {
        return ApiRes.success(stockBuyPlanManager.aiRecommend());
    }
}
