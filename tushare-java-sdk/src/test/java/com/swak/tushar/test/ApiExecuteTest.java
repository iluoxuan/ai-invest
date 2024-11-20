package com.swak.tushar.test;

import com.alibaba.fastjson2.JSON;
import com.swak.tushar.SwakTusharAutoConfig;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.basic.StockInfo;
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

        List<StockInfo> stockInfo = defaultApiExecute.execute(ApiNameEnum.stock_basic, null, StockInfo.class);

        log.info("stockInfo: {}", JSON.toJSONString(stockInfo));

    }
}
