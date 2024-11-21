package com.swak.tushar.entity.basic;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ai 生成
 * 股票公司信息类
 */
@Data
public class StockCompany {

    /**
     * 股票代码
     */
    private String tsCode;

    /**
     * 公司全称
     */
    private String comName;

    /**
     * 统一社会信用代码
     */
    private String comId;

    /**
     * 交易所代码
     */
    private String exchange;

    /**
     * 法人代表
     */
    private String chairman;

    /**
     * 总经理
     */
    private String manager;

    /**
     * 董秘
     */
    private String secretary;

    /**
     * 注册资本(万元)
     */
    private BigDecimal regCapital;

    /**
     * 注册日期
     */
    private String setupDate;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 公司介绍
     */
    private String introduction;

    /**
     * 公司主页
     */
    private String website;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 办公室
     */
    private String office;

    /**
     * 员工人数
     */
    private Integer employees;

    /**
     * 主要业务及产品
     */
    private String mainBusiness;

    /**
     * 经营范围
     */
    private String businessScope;
}