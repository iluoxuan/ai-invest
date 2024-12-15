package com.swak.ai.invest.service.stock;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账号股票基本信息
 *
 * @author: ljq
 * @date: 2024/12/6
 */
@Data
public class StockInfo {

    private String tsCode;

    private String symbol;

    private BigDecimal pe;

    /**
     * 总市值
     */
    private String totalMv;

    /**
     * 股票中文名
     */
    private String cnName;

    private BigDecimal price;

    private BigDecimal low1y;

    private BigDecimal low10y;


}
