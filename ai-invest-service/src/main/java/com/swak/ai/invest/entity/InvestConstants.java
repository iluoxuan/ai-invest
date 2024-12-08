package com.swak.ai.invest.entity;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
public interface InvestConstants {

    int defaultPlanAmount = 10 * 10000;

    int searchByStock = 5;


    interface urlPath {

        String xueqiu_quote = "/v5/stock/quote.json";
    }
}
