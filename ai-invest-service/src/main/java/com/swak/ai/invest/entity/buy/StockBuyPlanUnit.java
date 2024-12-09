package com.swak.ai.invest.entity.buy;

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
    private BigDecimal totalLoss;

    // 当前每股平均成本
    private BigDecimal buyAvgPrice;

    // 买入金额
    private BigDecimal buyAmount;

    // 涨跌幅
    private BigDecimal fallRate;

    // pe
    private BigDecimal pe;

    // 每股买入价格
    private BigDecimal currentPrice;

    // 当前公司总市值
    private BigDecimal currentTotalMv;

    // 买入股数
    private int shares;


}
