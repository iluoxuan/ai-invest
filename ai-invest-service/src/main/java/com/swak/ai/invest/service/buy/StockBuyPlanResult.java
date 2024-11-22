package com.swak.ai.invest.service.buy;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyPlanResult {

    private String tsCode;

    // 总成本
    private BigDecimal totalCost;

    // 平均成本
    private BigDecimal averageCost;

    // 亏损金额
    private BigDecimal lossAmount;

    // 亏损比例
    private BigDecimal lossRatio;

    private BigDecimal pe;



}
