package com.swak.ai.invest.entity.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/12/4
 */
@Data
public class AccountInitReq {

    @NotNull
    @ApiModelProperty("计划资金")
    private BigDecimal planAmount;

    @NotNull
    @ApiModelProperty("总现金资产")
    private BigDecimal totalAmount;

    @NotNull
    @ApiModelProperty("每月支出")
    private BigDecimal monthlyExpenses;

    @NotNull
    @ApiModelProperty("每月收入")
    private BigDecimal monthlyIncome;

}
