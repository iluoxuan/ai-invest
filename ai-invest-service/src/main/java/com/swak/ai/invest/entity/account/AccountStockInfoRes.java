package com.swak.ai.invest.entity.account;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账号股票基本信息
 *
 * @author: ljq
 * @date: 2024/12/6
 */
@Data
public class AccountStockInfoRes {

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
    private String stockCnName;

    public void setTotalMv(BigDecimal totalMv) {

    }


}
