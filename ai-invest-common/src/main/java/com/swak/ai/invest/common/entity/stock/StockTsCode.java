package com.swak.ai.invest.common.entity.stock;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Getter
@Setter
public class StockTsCode {

    private String tsCode;

    private String market;

    private String symbol;

    public static StockTsCode create(String tsCode) {
        String[] strings = StrUtil.splitToArray(tsCode, '.');
        Assert.isTrue(strings.length == 2, "tsCode格式不正确");
        StockTsCode code = new StockTsCode();
        code.setTsCode(tsCode);
        code.setSymbol(strings[0]);
        code.setMarket(strings[1]);
        return code;
    }

    public static void main(String[] args) {
        StockTsCode code = new StockTsCode().create("600000.SH");
    }
}
