package com.swak.ai.invest.service.buy;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyPlanUnit {

    // 总成本
    private BigDecimal totalBuyAmount;

    // 亏损金额
    private BigDecimal totalLossAmount;

    // 平均成本
    private BigDecimal averageCost;

    // 买入金额
    private BigDecimal buyAmount;

    // 涨跌幅
    private BigDecimal pctChg;

    // pe
    private BigDecimal pe;

    // 每股买入价格
    private BigDecimal buyPrice;

    // 当前公司总市值
    private BigDecimal totalMv;

    // 买入股数
    private int buyShares;


}
