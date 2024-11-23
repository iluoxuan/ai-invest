package com.swak.ai.invest.service.data;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Data
public class StockQuote {

    private String tsCode;

    // 当前估计
    private double currentPrice;

    // 涨跌幅
    private double chg;


}
