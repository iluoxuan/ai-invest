package com.swak.ai.invest.data.stock.xueqiu;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 雪球kline
 *
 * @author: ljq
 * @date: 2024/12/14
 */
@Data
public class XueQiuKline {

    /**
     * 时间戳，表示该K线数据的时间点
     */
    private long timestamp;

    /**
     * 成交量，表示该时间段内的交易数量
     */
    private BigDecimal volume;

    /**
     * 开盘价，表示该时间段的开盘价格
     */
    private BigDecimal open;

    /**
     * 最高价，表示该时间段内的最高价格
     */
    private BigDecimal high;

    /**
     * 最低价，表示该时间段内的最低价格
     */
    private BigDecimal low;

    /**
     * 收盘价，表示该时间段的收盘价格
     */
    private BigDecimal close;

    /**
     * 涨跌额，表示该时间段内的涨跌金额
     */
    private BigDecimal chg;

    /**
     * 涨跌幅，表示该时间段内的涨跌百分比
     */
    private BigDecimal percent;

    /**
     * 换手率，表示该时间段内的换手率
     */
    private BigDecimal turnoverrate;

    /**
     * 成交额，表示该时间段内的成交金额
     */
    private BigDecimal amount;

    /**
     * 后复权成交量，表示该时间段内的后复权成交量
     */
    @JSONField(name = "volume_post")
    private BigDecimal volumePost;

    /**
     * 后复权成交额，表示该时间段内的后复权成交金额
     */
    @JSONField(name = "amount_post")
    private BigDecimal amountPost;

    /**
     * 市盈率（PE），表示股票的市盈率
     */
    private BigDecimal pe;

    /**
     * 市净率（PB），表示股票的市净率
     */
    private BigDecimal pb;

    /**
     * 市销率（PS），表示股票的市销率
     */
    private BigDecimal ps;

    /**
     * 市现率（PCF），表示股票的市现率
     */
    private BigDecimal pcf;

    /**
     * 总市值，表示股票的总市值
     */
    @JSONField(name = "market_capital")
    private BigDecimal marketCapital;

    /**
     * 流通市值，表示股票的流通市值
     */
    private BigDecimal balance;

    /**
     * 内地持股量，表示内地投资者持有的股票数量
     */
    @JSONField(name = "hold_volume_cn")
    private BigDecimal holdVolumeCn;

    /**
     * 内地持股比例，表示内地投资者持有的股票占总股本的比例
     */
    @JSONField(name = "hold_ratio_cn")
    private BigDecimal holdRatioCn;

    /**
     * 内地净买入量，表示内地投资者净买入的股票数量
     */
    @JSONField(name = "net_volume_cn")
    private BigDecimal netVolumeCn;

    /**
     * 港股持股量，表示港股投资者持有的股票数量
     */
    @JSONField(name = "hold_volume_hk")
    private BigDecimal holdVolumeHk;

    /**
     * 港股持股比例，表示港股投资者持有的股票占总股本的比例
     */
    @JSONField(name = "hold_ratio_hk")
    private BigDecimal holdRatioHk;

    /**
     * 港股净买入量，表示港股投资者净买入的股票数量
     */
    @JSONField(name = "net_volume_hk")
    private BigDecimal netVolumeHk;
}
