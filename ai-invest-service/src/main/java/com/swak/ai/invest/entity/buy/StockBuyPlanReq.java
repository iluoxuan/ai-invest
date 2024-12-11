package com.swak.ai.invest.entity.buy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@ApiModel("股票加仓计划")
@Data
public class StockBuyPlanReq {

    @ApiModelProperty("股票ts唯一标记")
    private String tsCode;

    @ApiModelProperty("涨跌幅度")
    private Double fallRate = 0.02;

    @ApiModelProperty("计划加仓金额")
    private BigDecimal planAmount;

    @ApiModelProperty("计划名称")
    private StockBuyPlanName planName = StockBuyPlanName.leftBuy;
}
