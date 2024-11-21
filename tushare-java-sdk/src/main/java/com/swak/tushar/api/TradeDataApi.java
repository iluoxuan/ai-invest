package com.swak.tushar.api;

import com.swak.tushar.entity.trade.TradeLine;
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
    List<TradeLine> daily(TradeReq req);

    /**
     * 周线行情
     */
    List<TradeLine> weekly(TradeReq req);

    /**
     * 月线行情
     */
    List<TradeLine> monthly(TradeReq req);
}

