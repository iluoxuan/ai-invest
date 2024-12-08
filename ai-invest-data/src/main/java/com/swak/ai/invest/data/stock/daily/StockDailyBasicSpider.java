package com.swak.ai.invest.data.stock.daily;

import com.swak.tushar.entity.trade.StockDailyBasic;

/**
 * 收盘后同步
 *
 * @author: ljq
 * @date: 2024/12/8
 */
public interface StockDailyBasicSpider {

    StockDailyBasic spider(String tsCode) throws Exception;
}
