package com.swak.ai.invest.service.buy;

import com.swak.lib.common.number.BigNumber;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Data
public class StockCalculator {

    // pe
    private BigDecimal pe;

    // 当前股价
    private BigNumber price;

    // 涨跌幅
    private BigNumber pctChg;

    // 计算每股收益（EPS）
    private BigDecimal esp;

    // 1 涨 2 跌
    private int up = 2;

    private StockCalculator() {
    }

    public StockCalculator(BigDecimal pe, BigNumber price, BigNumber pctChg) {
        this.pe = pe;
        this.price = price;
        this.pctChg = pctChg;
        this.esp = price.divScale2(pe).getValue();
    }

    public StockCalculator calculator() {

        StockCalculator newCalculator = new StockCalculator();
        BigNumber newPrice = price.mul(BigNumber.ONE.sub(pctChg)).round2HalfUp();
        newCalculator.setPrice(newPrice);
        newCalculator.setPe(newPrice.divScale2(esp).getValue());
        return newCalculator;
    }
}
