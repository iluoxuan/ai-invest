package com.swak.ai.invest.data.stock.quote;

import com.swak.ai.invest.common.entity.stock.StockQuote;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public interface StockQuoteSpider {

    StockQuote spider(String tsCode) throws Exception;

}
