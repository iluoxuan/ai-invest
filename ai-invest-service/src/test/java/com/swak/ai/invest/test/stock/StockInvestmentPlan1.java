package com.swak.ai.invest.test.stock;

public class StockInvestmentPlan1 {
    public static void main(String[] args) {
        // 初始参数
        String stockCode = "01234";
        // 当前股价
        double initialPrice = 80.00;

        // 最低买入股数
        int minShares = 100;

        // 计划总投资金额
        double totalInvestment = 100000.0;

        // 预计最低股价
        double minPrice = 60.0;

        // 每次跌幅比例
        double fallRate = 0.01;

        // 计算最大加仓次数
        int maxBuys = (int) Math.floor((initialPrice - minPrice) / (initialPrice * fallRate));

        // 初始化变量
        double currentPrice = initialPrice;
        double totalCost = 0.0;
        int totalShares = 0;

        System.out.println("股票代码: " + stockCode);
        System.out.println("初始股价: " + initialPrice);
        System.out.println("预计最低股价: " + minPrice);
        System.out.println("每次跌幅: " + (fallRate * 100) + "%");
        System.out.println("最低加仓数量: " + minShares + "股");
        System.out.println("预计总投资金额: " + totalInvestment + "元");
        System.out.println("最大加仓次数: " + maxBuys);
        System.out.println();

        for (int i = 0; i <= maxBuys; i++) {
            // 计算当前加仓后的总成本和总持股数
            if (i > 0) {
                totalCost += currentPrice * minShares;
                totalShares += minShares;
            }

            // 计算当前持仓平均成本
            double averageCost = totalCost / totalShares;

            // 计算当前持仓亏损
            double currentLoss = (currentPrice - averageCost) * totalShares;

            // 输出当前信息
            System.out.println("第" + i + "次加仓:");
            System.out.println("当前股价: " + currentPrice);
            System.out.println("跌幅: " + (1 - (currentPrice / initialPrice)) * 100 + "%");
            System.out.println("PE值: " + (currentPrice * totalShares / totalCost));
            System.out.println("当前持仓亏损: " + currentLoss + "元");
            System.out.println("买入的总金额: " + totalCost + "元");
            System.out.println();

            // 更新当前股价
            if (i < maxBuys) {
                currentPrice *= (1 - fallRate);
            }
        }
    }
}
