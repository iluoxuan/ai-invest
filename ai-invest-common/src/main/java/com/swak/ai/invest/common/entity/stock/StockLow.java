package com.swak.ai.invest.common.entity.stock;

import cn.hutool.core.collection.CollectionUtil;
import com.swak.ai.invest.common.tools.BigDecimalTools;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: ljq
 * @date: 2024/12/10
 */
@Data
public class StockLow {

    private String tsCode;

    // 一年最低
    private BigDecimal low1y;

    // 2年最低
    private BigDecimal low2Y;

    // 3年最低
    private BigDecimal low3y;

    // 5年最低
    private BigDecimal low5y;

    // 十年最低
    private BigDecimal low10y;

    public List<BigDecimal> calculateDeclinePercentages() {
        List<BigDecimal> prices = CollectionUtil.newArrayList(low1y, low2Y, low3y, low5y, low10y);

        Collections.sort(prices);

        BigDecimal firstLowestPrice = prices.get(0);
        List<BigDecimal> lossPercentages = new ArrayList<>();

        for (int i = 1; i < prices.size(); i++) {
            BigDecimal nextPrice = prices.get(i);
            BigDecimal loss = firstLowestPrice.subtract(nextPrice);
            BigDecimal lossPercentage = loss.divide(firstLowestPrice, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimalTools.HUNDRED);
            lossPercentages.add(lossPercentage.setScale(2));
        }

        return lossPercentages;
    }

    public static void main(String[] args) {
        StockLow stockLow = new StockLow();
        stockLow.setLow1y(new BigDecimal("59.00"));
        stockLow.setLow2Y(new BigDecimal("70.00"));
        stockLow.setLow3y(new BigDecimal("65.00"));
        stockLow.setLow5y(new BigDecimal("60.00"));
        stockLow.setLow10y(new BigDecimal("55.00"));

        List<BigDecimal> declinePercentages = stockLow.calculateDeclinePercentages();
        System.out.println(declinePercentages);
    }

}
