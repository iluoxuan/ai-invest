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
 * 股票每日指标
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Getter
@Setter
@TableName("stock_daily_basic")
@ApiModel(value = "StockDailyBasicDo对象", description = "股票每日指标")
public class StockDailyBasicDo {

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

    @ApiModelProperty("换手率（%）")
    @TableField("turnover_rate")
    private BigDecimal turnoverRate;

    @ApiModelProperty("换手率（自由流通股）")
    @TableField("turnover_rate_f")
    private BigDecimal turnoverRateF;

    @ApiModelProperty("量比")
    @TableField("volume_ratio")
    private BigDecimal volumeRatio;

    @ApiModelProperty("市盈率（总市值/净利润， 亏损的PE为空）")
    @TableField("pe")
    private BigDecimal pe;

    @ApiModelProperty("市盈率（TTM，亏损的PE为空）")
    @TableField("pe_ttm")
    private BigDecimal peTtm;

    @ApiModelProperty("市净率（总市值/净资产）")
    @TableField("pb")
    private BigDecimal pb;

    @ApiModelProperty("市销率")
    @TableField("ps")
    private BigDecimal ps;

    @ApiModelProperty("市销率（TTM）")
    @TableField("ps_ttm")
    private BigDecimal psTtm;

    @ApiModelProperty("股息率 （%）")
    @TableField("dv_ratio")
    private BigDecimal dvRatio;

    @ApiModelProperty("股息率（TTM）（%）")
    @TableField("dv_ttm")
    private BigDecimal dvTtm;

    @ApiModelProperty("总股本 （万股）")
    @TableField("total_share")
    private BigDecimal totalShare;

    @ApiModelProperty("流通股本 （万股）")
    @TableField("float_share")
    private BigDecimal floatShare;

    @ApiModelProperty("自由流通股本 （万）")
    @TableField("free_share")
    private BigDecimal freeShare;

    @ApiModelProperty("总市值 （万元）")
    @TableField("total_mv")
    private BigDecimal totalMv;

    @ApiModelProperty("流通市值（万元）")
    @TableField("circ_mv")
    private BigDecimal circMv;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
