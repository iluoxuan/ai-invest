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
 * 用户投资账户
 * </p>
 *
 * @author ljq
 * @since 2024-12-04
 */
@Getter
@Setter
@TableName("user_invest_account")
@ApiModel(value = "UserInvestAccountDo对象", description = "用户投资账户")
public class UserInvestAccountDo {

    @ApiModelProperty("主键")
    @TableId(value = "account_id", type = IdType.INPUT)
    private String accountId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("计划资金")
    @TableField("plan_amount")
    private BigDecimal planAmount;

    @ApiModelProperty("总现金资产")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    @ApiModelProperty("每月支出")
    @TableField("monthly_expenses")
    private BigDecimal monthlyExpenses;

    @ApiModelProperty("每月收入")
    @TableField("monthly_income")
    private BigDecimal monthlyIncome;

    @ApiModelProperty("实际资金")
    @TableField("actual_amount")
    private BigDecimal actualAmount;

    @ApiModelProperty("已用资金")
    @TableField("used_amount")
    private BigDecimal usedAmount;

    @ApiModelProperty("冻结资金")
    @TableField("frozen_amount")
    private BigDecimal frozenAmount;

    @ApiModelProperty("可用资金")
    @TableField("available_amount")
    private BigDecimal availableAmount;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
