package com.swak.ai.invest.data.stock;

import com.swak.ai.inverst.common.entity.stock.StockQuote;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public interface StockQuoteSpider {

    StockQuote spider(String tsCode);

}
