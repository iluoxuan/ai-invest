package com.swak.ai.invest.dao.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 股票信息表
 * </p>
 *
 * @author ljq
 * @since 2024-11-21
 */
@Getter
@Setter
@TableName("stock")
@ApiModel(value = "StockDo对象", description = "股票信息表")
public class StockDo {

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("股票的TS代码，用于唯一标识一只股票")
    @TableField("ts_code")
    private String tsCode;

    @ApiModelProperty("股票代码，通常由数字组成")
    @TableField("symbol")
    private String symbol;

    @ApiModelProperty("股票名称，用于标识股票的中文名")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("地域信息，如省份或城市")
    @TableField("area")
    private String area;

    @ApiModelProperty("所属行业，用于分类股票")
    @TableField("industry")
    private String industry;

    @ApiModelProperty("股票的完整名称，可能包括公司名称等详细信息")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty("英文全称，国际投资者参考")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty("股票的拼音缩写，便于快速搜索")
    @TableField("cn_spell")
    private String cnSpell;

    @ApiModelProperty("市场类型，如主板、创业板等")
    @TableField("market")
    private String market;

    @ApiModelProperty("交易所代码，如SZSE表示深圳证券交易所")
    @TableField("`exchange`")
    private String exchange;

    @ApiModelProperty("交易货币，如CNY表示人民币")
    @TableField("curr_type")
    private String currType;

    @ApiModelProperty("上市状态，L表示已上市，D表示已退市，P表示暂停上市")
    @TableField("list_status")
    private String listStatus;

    @ApiModelProperty("上市日期，格式为YYYY-MM-DD")
    @TableField("list_date")
    private Date listDate;

    @ApiModelProperty("退市日期，如果有，格式为YYYY-MM-DD")
    @TableField("de_list_date")
    private Date deListDate;

    @ApiModelProperty("是否沪深港通标的，N表示不是，H表示沪股通，S表示深股通")
    @TableField("is_hs")
    private String isHs;

    @ApiModelProperty("实际控制人名称，即拥有实际控制权的个人或企业名称")
    @TableField("act_name")
    private String actName;

    @ApiModelProperty("实际控制人的企业性质，如国有企业、民营企业等")
    @TableField("act_ent_type")
    private String actEntType;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
