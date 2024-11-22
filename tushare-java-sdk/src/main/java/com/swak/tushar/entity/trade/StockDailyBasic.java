package com.swak.tushar.entity.trade;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 每日指标
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockDailyBasic {

    /**
     * TS股票代码
     */
    private String tsCode;

    /**
     * 交易日期
     */
    private LocalDate tradeDate;

    /**
     * 当日收盘价
     */
    private BigDecimal close;

    /**
     * 换手率（%）
     */
    private BigDecimal turnoverRate;

    /**
     * 换手率（自由流通股）
     */
    private BigDecimal turnoverRateF;

    /**
     * 量比
     */
    private BigDecimal volumeRatio;

    /**
     * 市盈率（总市值/净利润， 亏损的PE为空）
     */
    private BigDecimal pe;

    /**
     * 市盈率（TTM，亏损的PE为空）
     */
    private BigDecimal peTtm;

    /**
     * 市净率（总市值/净资产）
     */
    private BigDecimal pb;

    /**
     * 市销率
     */
    private BigDecimal ps;

    /**
     * 市销率（TTM）
     */
    private BigDecimal psTtm;

    /**
     * 股息率 （%）
     */
    private BigDecimal dvRatio;

    /**
     * 股息率（TTM）（%）
     */
    private BigDecimal dvTtm;

    /**
     * 总股本 （万股）
     */
    private BigDecimal totalShare;

    /**
     * 流通股本 （万股）
     */
    private BigDecimal floatShare;

    /**
     * 自由流通股本 （万）
     */
    private BigDecimal freeShare;

    /**
     * 总市值 （万元）
     */
    private BigDecimal totalMv;

    /**
     * 流通市值（万元）
     */
    private BigDecimal circMv;

}
