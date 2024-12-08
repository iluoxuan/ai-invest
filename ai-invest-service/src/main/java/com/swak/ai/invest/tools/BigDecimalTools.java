package com.swak.ai.invest.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
public class BigDecimalTools {

    public final static BigDecimal TEN_THOUSAND = new BigDecimal(10000);

    // 亿
    public final static BigDecimal BILLION = new BigDecimal("100000000");

    // 万亿
    public final static BigDecimal TRILLION = new BigDecimal("1000000000000");


    public static String format(BigDecimal value) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(value);
    }
}
