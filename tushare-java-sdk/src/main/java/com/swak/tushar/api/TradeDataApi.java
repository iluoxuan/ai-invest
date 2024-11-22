package com.swak.tushar.api;

import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;
import com.swak.tushar.entity.trade.TradeReq;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/21
 */
public interface TradeDataApi {


    /**
     * 日线行情
     */
    List<StockTradeLine> daily(TradeReq req);

    /**
     * 周线行情
     */
    List<StockTradeLine> weekly(TradeReq req);

    /**
     * 月线行情
     */
    List<StockTradeLine> monthly(TradeReq req);

    /**
     * 每日指标
     */
    List<StockDailyBasic> stockDailyBasic(TradeReq req);
}

