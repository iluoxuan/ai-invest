package com.swak.ai.invest.entity.buy;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 买入计划结果
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyPlanResult {

    // 计划买入金额
    private BigDecimal planBuyAmount;

    private List<StockBuyPlanUnit> buyPlanUnits = new ArrayList<>();

    private int buyCnt;
}
