package com.swak.ai.invest.test.job;

import com.swak.ai.invest.job.StockDataSyncJob;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: ljq
 * @date: 2024/11/21
 */
@SpringBootTest
public class StockDataSyncJobTest {

    @Resource
    private StockDataSyncJob stockDataSyncJob;


    @Disabled
    @Test
    public void testSyncStockBasicList() {

        stockDataSyncJob.syncStockBasicList();
    }


    @Test
    public void testSyncDaily() {
        stockDataSyncJob.syncDaily();
    }

    @Test
    public void testSyncDailyByTsCode() {
        stockDataSyncJob.syncDailyByTsCode("09988.HK");
    }
}
