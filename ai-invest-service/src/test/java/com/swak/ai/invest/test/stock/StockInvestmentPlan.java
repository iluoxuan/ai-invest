package com.swak.ai.invest.test.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockInvestmentPlan {
    public static void main(String[] args) {
        // 初始参数
        String stockCode = "01234";
        BigDecimal initialPrice = new BigDecimal("80.00");
        int minShares = 100;
        BigDecimal totalInvestment = new BigDecimal("100000.00");
        BigDecimal minPrice = new BigDecimal("60.00");
        BigDecimal fallRate = new BigDecimal("0.01");

        // 计算最大加仓次数
        BigDecimal maxBuysTemp = initialPrice.subtract(minPrice)
                .divide(initialPrice.multiply(fallRate), RoundingMode.HALF_UP);
        int maxBuys = maxBuysTemp.intValue();

        // 初始化变量
        BigDecimal currentPrice = initialPrice;
        BigDecimal totalCost = BigDecimal.ZERO;
        int totalShares = 0;

        System.out.println("股票代码: " + stockCode);
        System.out.println("初始股价: " + initialPrice);
        System.out.println("预计最低股价: " + minPrice);
        System.out.println("每次跌幅: " + fallRate.multiply(new BigDecimal("100")) + "%");
        System.out.println("最低加仓数量: " + minShares + "股");
        System.out.println("预计总投资金额: " + totalInvestment + "元");
        System.out.println("最大加仓次数: " + maxBuys);
        System.out.println();

        for (int i = 0; i <= maxBuys; i++) {
            // 计算当前加仓后的总成本和总持股数
            totalCost = totalCost.add(currentPrice.multiply(new BigDecimal(minShares)))
                    .setScale(2, RoundingMode.HALF_UP);
            totalShares += minShares;


            // 计算当前持仓平均成本
            BigDecimal averageCost = totalCost.divide(new BigDecimal(totalShares), 2, RoundingMode.HALF_UP);

            // 计算当前持仓亏损
            BigDecimal currentLoss = currentPrice.subtract(averageCost)
                    .multiply(new BigDecimal(totalShares))
                    .setScale(2, RoundingMode.HALF_UP);

            // 计算当前PE值
            BigDecimal peValue = currentPrice.multiply(new BigDecimal(totalShares))
                    .divide(totalCost, 2, RoundingMode.HALF_UP);

            // 输出当前信息
            System.out.println("第" + i + "次加仓:");
            System.out.println("当前股价: " + currentPrice);
            System.out.println("跌幅: " + (1 - (currentPrice.divide(initialPrice, 4, RoundingMode.HALF_UP)
                    .doubleValue())) * 100 + "%");
            System.out.println("PE值: " + peValue);
            System.out.println("当前持仓亏损: " + currentLoss + "元");
            System.out.println("买入的总金额: " + totalCost + "元");
            System.out.println();

            // 更新当前股价
            if (i < maxBuys) {
                currentPrice = currentPrice.multiply(BigDecimal.ONE.subtract(fallRate))
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }
    }
}
