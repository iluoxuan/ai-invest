package com.swak.ai.invest.data.stock.base;

import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;

import java.util.List;

/**
 * 数据爬虫接口
 *
 * @author: ljq
 * @date: 2024/11/24
 */
public interface StockDataSpider {

    /**
     * 实时数据
     *
     * @param tsCode
     */
    StockQuote quote(String tsCode);

    /**
     * dailyLine数据
     */
    List<StockTradeLine> dailyLine(String tsCode) ;

    /**
     * dailyBasic数据
     */
    StockDailyBasic dailyBasic(String tsCode);

}
