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
 * 账户持仓
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Getter
@Setter
@TableName("account_stock_position")
@ApiModel(value = "AccountStockPositionDo对象", description = "账户持仓")
public class AccountStockPositionDo {

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
    @TableField("quantity")
    private Integer quantity;

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

    @ApiModelProperty("涨跌额")
    @TableField("`change`")
    private BigDecimal change;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
