package com.swak.ai.invest.service.stock;

import cn.hutool.core.util.NumberUtil;
import com.swak.ai.invest.common.tools.BigDecimalTools;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

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

    /**
     * 设置单位是 亿
     */
    public void setTotalMv(BigDecimal totalMv) {
        if (Objects.isNull(totalMv)) {
            return;
        }

        if (NumberUtil.isGreater(totalMv, BigDecimalTools.TRILLION)) {
            BigDecimal value = NumberUtil.div(totalMv, BigDecimalTools.TRILLION, 3);
            this.totalMv = BigDecimalTools.format(value) + "万亿";
            return;
        }
        if (NumberUtil.isGreater(totalMv, BigDecimalTools.BILLION)) {
            BigDecimal value = NumberUtil.div(totalMv, BigDecimalTools.BILLION, 3);
            this.totalMv = BigDecimalTools.format(value) + "亿";
            return;
        }
        this.totalMv = "不足亿";
    }


}
