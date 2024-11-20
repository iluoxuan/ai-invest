package com.swak.tushar.entity.basic;

import lombok.Data;

/**
 * 股票基本信息类
 */
@Data
public class StockInfo {

    // 股票的TS代码，用于唯一标识一只股票
    private String tsCode;

    // 股票代码，通常由数字组成
    private String symbol;

    // 股票名称，用于标识股票的中文名
    private String name;

    // 地域信息，如省份或城市
    private String area;

    // 所属行业，用于分类股票
    private String industry;

    // 股票的完整名称，可能包括公司名称等详细信息
    private String fullName;

    // 英文全称，国际投资者参考
    private String enName;

    // 股票的拼音缩写，便于快速搜索
    private String cnspell;

    // 市场类型，如主板、创业板等
    private String market;

    // 交易所代码，如SZSE表示深圳证券交易所
    private String exchange;

    // 交易货币，如CNY表示人民币
    private String currType;

    // 上市状态，L表示已上市，D表示已退市，P表示暂停上市
    private String listStatus;

    // 上市日期，格式为YYYY-MM-DD
    private String listDate;

    // 退市日期，如果有，格式为YYYY-MM-DD
    private String deListDate;

    // 是否沪深港通标的，N表示不是，H表示沪股通，S表示深股通
    private String isHs;

    // 实际控制人名称，即拥有实际控制权的个人或企业名称
    private String actName;

    // 实际控制人的企业性质，如国有企业、民营企业等
    private String actEntType;
}