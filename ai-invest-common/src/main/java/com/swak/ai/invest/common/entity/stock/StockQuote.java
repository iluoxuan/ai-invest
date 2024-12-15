package com.swak.ai.invest.common.entity.stock;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Data
public class StockQuote {

    private String tsCode;

    // 当前估计
    private BigDecimal currentPrice;

    // 涨跌幅
    private BigDecimal chg;

    private BigDecimal pe;

    // 52周最低
    private BigDecimal low52w;

    // 52周最高
    private BigDecimal high52w;

    // 涨跌幅
    private BigDecimal percent;

    // 股票名称
    private String name;

    // 交易单位 手数
    private int lotSize;

    /**
     * 交易所代码。
     * 表示股票所在的交易所代码，如 "SZ" 表示深圳证券交易所，"SH" 表示上海证券交易所。
     */
    private String exchange;

    private BigDecimal marketCapital;

    /**
     * 股息。
     * 表示股票的股息。
     */
    private BigDecimal dividend;

    public boolean isEmpty() {
        return ObjectUtil.hasEmpty(currentPrice, pe);
    }


}
