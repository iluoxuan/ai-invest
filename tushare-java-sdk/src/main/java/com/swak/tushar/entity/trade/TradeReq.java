package com.swak.tushar.entity.trade;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/11/21
 */
@Data
public class TradeReq {


    // 股票代码（支持多个股票同时提取，逗号分隔）
    private String ts_code;

    // 交易日期（格式：YYYYMMDD）
    private String trade_date;

    // 开始日期（格式：YYYYMMDD）
    private String start_date;

    // 结束日期（格式：YYYYMMDD）
    private String end_date;

}
