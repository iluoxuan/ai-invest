package com.swak.ai.invest.data.xueqiu;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 股票实时行情实体类
 */
@Data
public class XueQiuStockQuote {


    /**
     * 股票代码，例如 "SZ000001"。
     * 格式通常为市场标识符后跟股票代码，如 "SZ" 表示深圳证券交易所，"SH" 表示上海证券交易所。
     */
    private String symbol;

    /**
     * 52周最高价。
     * 表示过去52周内的最高价格。
     */
    private BigDecimal high52w;

    /**
     * 延迟标志。
     * 表示数据的延迟情况，0表示无延迟。
     */
    private int delayed;

    /**
     * 最小变动单位。
     * 表示股票价格的最小变动单位。
     */
    private BigDecimal tickSize;

    /**
     * 流通股本（股）。
     * 表示股票的流通股本数量。
     */
    private long floatShares;

    /**
     * 跌停价。
     * 表示股票的跌停价格。
     */
    private BigDecimal limitDown;

    /**
     * 是否亏损。
     * 表示公司是否处于亏损状态，"N" 表示不亏损。
     */
    private String noProfit;

    /**
     * 最高价。
     * 表示股票在当前交易日的最高价格。
     */
    private BigDecimal high;

    /**
     * 流通市值（元）。
     * 表示股票的流通市值。
     */
    private BigDecimal floatMarketCapital;

    /**
     * 单位手数。
     * 表示股票交易的最小单位手数。
     */
    @JSONField(name = "lot_size")
    private int lotSize;


    /**
     * 加权投票权。
     * 表示股票的加权投票权信息，"N" 表示无差异。
     */
    private String weightedVotingRights;

    /**
     * 涨跌额。
     * 表示股票在当前交易日的涨跌金额。
     */
    private BigDecimal chg;

    /**
     * 每股收益（EPS）。
     * 表示每股的净利润。
     */
    private BigDecimal eps;

    /**
     * 昨收价。
     * 表示股票在前一天的收盘价。
     */
    @JSONField(name = "last_close")
    private BigDecimal lastClose;

    /**
     * 四季度利润。
     * 表示公司四季度的利润。
     */
    private BigDecimal profitFour;

    /**
     * 成交量（股）。
     * 表示股票在当前交易日的成交量。
     */
    private long volume;

    /**
     * 成交量比。
     * 表示股票在当前交易日的成交量与前一交易日相比的变化比例。
     */
    @JSONField(name = "volume_ratio")
    private BigDecimal volumeRatio;

    /**
     * 预测利润。
     * 表示公司预测的利润。
     */
    private BigDecimal profitForecast;

    /**
     * 换手率。
     * 表示股票在当前交易日的换手率。
     */
    private BigDecimal turnoverRate;

    /**
     * 52周最低价。
     * 表示过去52周内的最低价格。
     */
    private BigDecimal low52w;

    /**
     * 股票名称。
     * 表示股票的名称。
     */
    private String name;

    /**
     * 交易所代码。
     * 表示股票所在的交易所代码，如 "SZ" 表示深圳证券交易所，"SH" 表示上海证券交易所。
     */
    private String exchange;

    /**
     * 预测市盈率（PE）。
     * 表示股票的预测市盈率。
     */
    private BigDecimal peForecast;

    /**
     * 总股本（股）。
     * 表示股票的总股本数量。
     */
    private long totalShares;

    /**
     * 状态。
     * 表示股票的状态代码。
     */
    private int status;

    /**
     * 是否为VIE结构描述。
     * 表示公司是否为VIE结构，"否" 表示不是。
     */
    private String isVieDesc;

    /**
     * 安全状态。
     * 可能包含股票的安全状态信息。
     */
    private Object securityStatus;

    /**
     * 股票代码。
     * 表示股票的代码。
     */
    private String code;

    /**
     * 商誉占净资产比例。
     * 表示公司商誉占净资产的比例。
     */
    @JSONField(name = "goodwill_in_net_assets")
    private BigDecimal goodwillInNetAssets;

    /**
     * 平均价。
     * 表示股票在当前交易日的均价。
     */
    @JSONField(name = "avg_price")
    private BigDecimal avgPrice;

    /**
     * 涨跌幅。
     * 表示股票在当前交易日的涨跌幅百分比。
     */
    private BigDecimal percent;

    /**
     * 加权投票权描述。
     * 表示股票的加权投票权描述，"无差异" 表示无差异。
     */
    private String weightedVotingRightsDesc;

    /**
     * 振幅。
     * 表示股票在当前交易日的价格振幅。
     */
    private BigDecimal amplitude;

    /**
     * 当前价。
     * 表示股票在当前交易日的最新价格。
     */
    private BigDecimal current;

    /**
     * 是否为VIE结构。
     * 表示公司是否为VIE结构，"N" 表示不是。
     */
    private String isVie;

    /**
     * 当年涨跌幅。
     * 表示股票在当年的涨跌幅百分比。
     */
    @JSONField(name = "current_year_percent")
    private BigDecimal currentYearPercent;

    /**
     * 发行日期。
     * 表示股票的发行日期，格式为时间戳。
     */
    private long issueDate;

    /**
     * 子类型。
     * 表示股票的子类型代码。
     */
    private String subType;

    /**
     * 最低价。
     * 表示股票在当前交易日的最低价格。
     */
    private BigDecimal low;

    /**
     * 是否为注册制描述。
     * 表示股票是否为注册制，"否" 表示不是。
     */
    private String isRegistrationDesc;

    /**
     * 是否亏损描述。
     * 表示公司是否处于亏损状态的描述，"已盈利" 表示已盈利。
     */
    private String noProfitDesc;

    /**
     * 总市值（元）。
     * 表示股票的总市值。
     */
    @JSONField(name = "market_capital")
    private BigDecimal marketCapital;

    /**
     * 股息。
     * 表示股票的股息。
     */
    private BigDecimal dividend;

    /**
     * 股息收益率。
     * 表示股票的股息收益率。
     */
    private BigDecimal dividendYield;

    /**
     * 货币代码。
     * 表示股票的货币代码，如 "CNY" 表示人民币。
     */
    private String currency;

    /**
     * 每股净资产（NAVPS）。
     * 表示每股的净资产价值。
     */
    private BigDecimal navps;

    /**
     * 利润。
     * 表示公司的利润。
     */
    private BigDecimal profit;

    /**
     * 时间戳。
     * 表示数据的时间戳。
     */
    private long timestamp;

    /**
     * 历史市盈率（PE）。
     * 表示股票的历史市盈率。
     */
    @JSONField(name = "pe_lyr")
    private BigDecimal peLyr;

    /**
     * 成交额（元）。
     * 表示股票在当前交易日的成交金额。
     */
    private BigDecimal amount;

    /**
     * 质押比例。
     * 表示股票的质押比例。
     */
    private BigDecimal pledgeRatio;


    /**
     * 是否为注册制。
     * 表示股票是否为注册制，"N" 表示不是。
     */
    private String isRegistration;

    /**
     * 市净率（PB）。
     * 表示股票的市净率。
     */
    private BigDecimal pb;

    /**
     * 涨停价。
     * 表示股票的涨停价格。
     */
    private BigDecimal limitUp;

    /**
     * TTM市盈率（PE）。
     * 表示股票的TTM市盈率。
     */
    @JSONField(name = "pe_ttm")
    private BigDecimal peTtm;

    /**
     * 时间。
     * 表示数据的时间戳。
     */
    private long time;

    /**
     * 开盘价。
     * 表示股票在当前交易日的开盘价。
     */
    private BigDecimal open;
}
