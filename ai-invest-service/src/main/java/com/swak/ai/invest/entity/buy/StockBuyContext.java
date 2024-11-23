package com.swak.ai.invest.entity.buy;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyContext {

    private String tsCode;

    private String accountId;


    // 每次跌幅比例
    private Double fallRate = 0.01;

}
