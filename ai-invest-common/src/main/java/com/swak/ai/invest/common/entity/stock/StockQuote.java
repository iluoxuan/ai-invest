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

    public boolean isEmpty() {
        return ObjectUtil.hasEmpty(currentPrice, pe);
    }


}
