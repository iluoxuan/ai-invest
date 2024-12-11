package com.swak.ai.invest.common.entity.stock;

import cn.hutool.core.collection.CollectionUtil;
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

        List<BigDecimal> declinePercentages = new ArrayList<>();
        for (int i = 0; i < prices.size() - 1; i++) {
            BigDecimal currentPrice = prices.get(i);
            BigDecimal nextPrice = prices.get(i + 1);
            BigDecimal decline = currentPrice.subtract(nextPrice);
            BigDecimal declinePercentage = decline.divide(currentPrice, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            declinePercentages.add(declinePercentage.setScale(2));
        }

        return declinePercentages;
    }

    public static void main(String[] args) {
        StockLow stockLow = new StockLow();
        stockLow.setLow1y(new BigDecimal("80.00"));
        stockLow.setLow2Y(new BigDecimal("70.00"));
        stockLow.setLow3y(new BigDecimal("65.00"));
        stockLow.setLow5y(new BigDecimal("60.00"));
        stockLow.setLow10y(new BigDecimal("55.00"));

        List<BigDecimal> declinePercentages = stockLow.calculateDeclinePercentages();
        System.out.println(declinePercentages);
    }

}
