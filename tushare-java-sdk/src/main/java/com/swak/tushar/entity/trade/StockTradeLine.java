package com.swak.tushar.entity.trade;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 日线行情
 */
@Data
public class StockTradeLine {

    /**
     * 股票代码，例如 "600000.SH"。
     */
    private String tsCode;

    /**
     * 交易日期，格式为 "YYYYMMDD"，例如 "20231001"。
     */
    private String tradeDate;

    /**
     * 开盘价。
     */
    private BigDecimal open;

    /**
     * 最高价。
     */
    private BigDecimal high;

    /**
     * 最低价。
     */
    private BigDecimal low;

    /**
     * 收盘价。
     */
    private BigDecimal close;

    /**
     * 昨收价【除权价，前复权】。
     */
    private BigDecimal preClose;

    /**
     * 涨跌额。
     */
    private BigDecimal change;

    /**
     * 涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收】。
     */
    private BigDecimal pctChg;

    /**
     * 成交量 （手）。
     */
    private BigDecimal vol;

    /**
     * 成交额 （千元）。
     */
    private BigDecimal amount;


}