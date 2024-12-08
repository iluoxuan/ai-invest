package com.swak.ai.invest.entity.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@Data
public class AccountInvestInfoRes {

    @ApiModelProperty("计划资金")
    private String planAmount;

    @ApiModelProperty("总现金资产")
    private String totalAmount;

    @ApiModelProperty("每月支出")
    private String monthlyExpenses;

    @ApiModelProperty("每月收入")
    private String monthlyIncome;

    @ApiModelProperty("实际资金")
    private String actualAmount;

    @ApiModelProperty("已用资金")
    private String usedAmount;

    @ApiModelProperty("冻结资金")
    private String frozenAmount;

    @ApiModelProperty("可用资金")
    private String availableAmount;
}
