package com.swak.ai.invest.data.stock.xueqiu;

import com.swak.ai.invest.common.entity.stock.StockTsCode;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public class XueQiuTools {

    public static String symbol(String tsCode) {
        // 转化为雪球的symbol
        StockTsCode stockTsCode = StockTsCode.create(tsCode);
        if(stockTsCode.isHk()){
            return stockTsCode.getSymbol();
        }
        return stockTsCode.getMarket().toUpperCase() + stockTsCode.getSymbol();

    }
}
