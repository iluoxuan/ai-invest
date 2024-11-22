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
 * 股票月线行情表
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Getter
@Setter
@TableName("stock_monthly_line")
@ApiModel(value = "StockMonthlyLineDo对象", description = "股票月线行情表")
public class StockMonthlyLineDo {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("股票代码")
    @TableField("ts_code")
    private String tsCode;

    @ApiModelProperty("交易日期")
    @TableField("trade_date")
    private Date tradeDate;

    @ApiModelProperty("开盘价")
    @TableField("`open`")
    private BigDecimal open;

    @ApiModelProperty("最高价")
    @TableField("high")
    private BigDecimal high;

    @ApiModelProperty("最低价")
    @TableField("low")
    private BigDecimal low;

    @ApiModelProperty("收盘价")
    @TableField("`close`")
    private BigDecimal close;

    @ApiModelProperty("昨收价【除权价，前复权】")
    @TableField("pre_close")
    private BigDecimal preClose;

    @ApiModelProperty("涨跌额")
    @TableField("`change`")
    private BigDecimal change;

    @ApiModelProperty("涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】")
    @TableField("pct_chg")
    private BigDecimal pctChg;

    @ApiModelProperty("成交量 （手）")
    @TableField("vol")
    private BigDecimal vol;

    @ApiModelProperty("成交额 （千元）")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
