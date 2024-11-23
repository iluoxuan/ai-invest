package com.swak.ai.invest.service.buy;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Data
public class StockBuyPlanReq {

    private String tsCode;

    private Double fallRate;

    private StockBuyPlanName planName;
}
