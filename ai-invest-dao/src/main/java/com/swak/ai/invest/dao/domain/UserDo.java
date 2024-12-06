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
 * 用户表
 * </p>
 *
 * @author ljq
 * @since 2024-12-06
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "UserDo对象", description = "用户表")
public class UserDo {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("用名称")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("头像")
    @TableField("header_img")
    private String headerImg;

    @ApiModelProperty("删除标志 1：注册用户   2：管理 ")
    @TableField("`type`")
    private Integer type;

    @ApiModelProperty("微信开发平台ID")
    @TableField("wx_id")
    private String wxId;

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
