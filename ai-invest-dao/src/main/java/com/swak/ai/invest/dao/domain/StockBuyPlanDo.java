package com.swak.ai.invest.dao.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 股票买入计划
 * </p>
 *
 * @author ljq
 * @since 2024-12-09
 */
@Getter
@Setter
@TableName("stock_buy_plan")
@ApiModel(value = "StockBuyPlanDo对象", description = "股票买入计划")
public class StockBuyPlanDo {

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("账户ID")
    @TableField("account_id")
    private String accountId;

    @ApiModelProperty("股票的TS代码")
    @TableField("ts_code")
    private String tsCode;

    @ApiModelProperty("持仓数量")
    @TableField("shares")
    private Integer shares;

    @ApiModelProperty("计划类型：1：leftBuy 2: rightBuy 3：aiBuy")
    @TableField("plan_type")
    private Integer planType;

    @ApiModelProperty("计划买入金额")
    @TableField("plan_amount")
    private BigDecimal planAmount;

    @ApiModelProperty("已用资金")
    @TableField("used_amount")
    private BigDecimal usedAmount;

    @ApiModelProperty("可用资金")
    @TableField("available_amount")
    private BigDecimal availableAmount;

    @ApiModelProperty("涨跌幅")
    @TableField("pct_chg")
    private BigDecimal pctChg;

    @ApiModelProperty("买入均价")
    @TableField("buy_avg_price")
    private BigDecimal buyAvgPrice;

    @ApiModelProperty("当前价格")
    @TableField("current_price")
    private BigDecimal currentPrice;

    @ApiModelProperty("当前pe")
    @TableField("current_pe")
    private BigDecimal currentPe;

    @ApiModelProperty("总亏损")
    @TableField("total_loss")
    private BigDecimal totalLoss;

    @ApiModelProperty("买入总金额")
    @TableField("total_buy_amount")
    private BigDecimal totalBuyAmount;

    @ApiModelProperty("总市值")
    @TableField("current_total_mv")
    private BigDecimal currentTotalMv;

    @ApiModelProperty("涨跌额")
    @TableField("`change`")
    private BigDecimal change;

    @ApiModelProperty("排序号")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
