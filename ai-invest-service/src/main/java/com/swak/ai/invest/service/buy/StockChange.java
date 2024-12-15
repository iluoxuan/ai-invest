package com.swak.ai.invest.service.buy;

import com.swak.lib.common.number.BigNumber;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 按涨跌幅 计算获取新的信息
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@Data
public class StockChange {

    // pe
    private BigDecimal pe;

    // 当前股价
    private BigNumber price;

    // 涨跌幅
    private BigNumber pctChg;

    // 当前总市值
    private BigNumber totalMv;

    // 计算每股收益（EPS）
    private BigDecimal esp;

    // 1 涨 2 跌
    private int up = 2;

    public StockChange(BigDecimal pe, BigNumber price, BigNumber totalMv, BigNumber pctChg) {

        this.pe = pe;
        this.price = price;
        this.pctChg = pctChg;
        this.esp = price.divScale2(pe).getValue();

        // 有涨跌幅重新计算
        if (BigNumber.ZERO.compareTo(pctChg) != 0) {
            changePrice();
            this.pe = this.price.divScale2(esp).getValue();
        }

        // 计算当前总市值
        // 直接使用跌幅计算新的总市值
        this.totalMv = this.totalMv.mul(BigNumber.ONE.add(pctChg)).round2HalfUp();


    }

    private void changePrice() {
        if (up == 2) {
            this.price = this.price.mul(BigNumber.ONE.sub(pctChg)).round2HalfUp();
            return;
        }
        this.price = this.price.mul(BigNumber.ONE.add(pctChg)).round2HalfUp();
    }
}
