package com.swak.tushar.test;

import com.alibaba.fastjson2.JSON;
import com.swak.tushar.SwakTusharAutoConfig;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.basic.StockCompany;
import com.swak.tushar.entity.trade.StockTradeLine;
import com.swak.tushar.entity.trade.TradeReq;
import com.swak.tushar.execute.DefaultApiExecute;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@Slf4j
@SpringBootTest(classes = SwakTusharAutoConfig.class)
public class ApiExecuteTest {

    @Resource
    private DefaultApiExecute defaultApiExecute;

    @Test
    public void testBasicData() {

        List<Stock> stock = defaultApiExecute.execute(ApiNameEnum.stock_basic, null, Stock.class);

        log.info("stockInfo: {}", JSON.toJSONString(stock));

    }

    @Test
    public void testStockCompany() {

        List<StockCompany> stockCompanies = defaultApiExecute.execute(ApiNameEnum.stock_company, null, StockCompany.class);

        log.info("stockCompanyInfo: {}", JSON.toJSONString(stockCompanies));
    }

    @Test
    public void testTradeLine(){

        TradeReq tradeReq = new TradeReq();
        tradeReq.setTs_code("873576.BJ");
        List<StockTradeLine> stockTradeLines = defaultApiExecute.execute(ApiNameEnum.daily, tradeReq, StockTradeLine.class);
        log.info("tradeLines: {}", JSON.toJSONString(stockTradeLines));
    }
}
