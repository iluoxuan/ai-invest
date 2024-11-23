package com.swak.ai.invest.service.buy;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyContext {

    private String tsCode;

    private String accountId;


    // 每次跌幅比例
    BigDecimal fallRate = NumberUtil.toBigDecimal("0.01");

}
