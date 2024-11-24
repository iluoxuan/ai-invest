package com.swak.ai.inverst.common.entity.stock;

import cn.hutool.core.util.StrUtil;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public class StockTools {

    public static String symbol(String tsCode) {
        return StrUtil.sub(tsCode, 0, StrUtil.indexOf(tsCode, '.'));
    }

    public static void main(String[] args) {
        System.out.println(StockTools.symbol("000001.SZ"));
    }
}
