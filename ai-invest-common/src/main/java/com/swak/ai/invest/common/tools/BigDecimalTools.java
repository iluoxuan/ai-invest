package com.swak.ai.invest.common.tools;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
public class BigDecimalTools {


    public final static BigDecimal HUNDRED = new BigDecimal(100);

    public final static BigDecimal TEN_THOUSAND = new BigDecimal(10000);

    // 亿
    public final static BigDecimal BILLION = new BigDecimal("100000000");

    // 万亿
    public final static BigDecimal TRILLION = new BigDecimal("1000000000000");


    public static String format(BigDecimal value) {
        if (Objects.isNull(value)) {
            return StrUtil.EMPTY;
        }
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(value);
    }

    /**
     * 设置单位是 亿
     */
    public static String formatMarketMv(BigDecimal totalMv) {
        if (Objects.isNull(totalMv)) {
            return StrUtil.EMPTY;
        }

        if (NumberUtil.isGreater(totalMv, BigDecimalTools.TRILLION)) {
            BigDecimal value = NumberUtil.div(totalMv, BigDecimalTools.TRILLION, 3);
            return BigDecimalTools.format(value) + "万亿";

        }
        if (NumberUtil.isGreater(totalMv, BigDecimalTools.BILLION)) {
            BigDecimal value = NumberUtil.div(totalMv, BigDecimalTools.BILLION, 3);
            return BigDecimalTools.format(value) + "亿";

        }
        return "1亿内";
    }

    public static void main(String[] args) {
        System.out.println(format(new BigDecimal("22123456789.123")));
    }
}
