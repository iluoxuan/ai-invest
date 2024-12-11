package com.swak.ai.invest.entity.buy;

import io.swagger.annotations.ApiModelProperty;
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


    @ApiModelProperty("计划加仓金额")
    private BigDecimal planAmount;

    // 每次跌幅比例
    private Double fallRate = 0.02;

}
