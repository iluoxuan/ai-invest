package com.swak.ai.invest.data.stock.quote;

import com.swak.ai.inverst.common.entity.stock.StockTsCode;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public class XueQiuTools {

    public static String symbol(String tsCode) {
        // 转化为雪球的symbol
        StockTsCode stockTsCode = StockTsCode.create(tsCode);
        return stockTsCode.getMarket().toUpperCase() + stockTsCode.getSymbol();

    }
}
