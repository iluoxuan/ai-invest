package com.swak.ai.invest.web;

import com.swak.ai.invest.service.buy.StockBuyPlanManager;
import com.swak.ai.invest.service.buy.StockBuyPlanReq;
import com.swak.ai.invest.service.buy.StockBuyPlanResult;
import com.swak.lib.client.entity.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@RequestMapping("/stock/position")
@RequiredArgsConstructor
@RestController
public class StockBuyController {

    private final StockBuyPlanManager stockBuyPlanManager;

    @PostMapping("/leftBuy")
    public ApiRes<StockBuyPlanResult> leftBuy(@Valid @RequestBody StockBuyPlanReq req) {

        return ApiRes.success(stockBuyPlanManager.buyPlan(req));
    }
}
