package com.swak.ai.invest.test.buy;

import com.alibaba.fastjson2.JSON;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.entity.buy.StockBuyContext;
import com.swak.ai.invest.entity.buy.StockBuyPlanResult;
import com.swak.ai.invest.service.buy.StockLeftFixedBuyStrategy;
import com.swak.lib.common.number.BigNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Slf4j
@SpringBootTest
public class StockBuyTest {

    @Resource
    private StockLeftFixedBuyStrategy stockLeftFixedBuyStrategy;

    @Resource
    private AccountStockPositionMapper accountStockPositionMapper;

    private final static String accountId = "123456";
    private final static String tsCode = "000001.SZ";

    @BeforeEach
    public void init() {


        AccountStockPositionDo positionDo = accountStockPositionMapper.getBy(accountId, tsCode);
        if (Objects.nonNull(positionDo)) {
            return;
        }

        positionDo = new AccountStockPositionDo();
        positionDo.setAccountId(accountId);
        positionDo.setTsCode(tsCode);
        positionDo.setPlanAmount(BigNumber.of(10 * 1000).getValue());
        positionDo.setCreateTime(new Date());
        positionDo.setAvailableAmount(positionDo.getPlanAmount());
        positionDo.setQuantity(0);
        positionDo.setUsedAmount(BigDecimal.ZERO);
        positionDo.setUpdateTime(new Date());

        accountStockPositionMapper.insert(positionDo);
    }

    @Test
    public void leftFixedBuyTest() {

        StockBuyContext context = new StockBuyContext();
        context.setTsCode(tsCode);
        context.setFallRate(0.01);
        context.setAccountId(accountId);

        StockBuyPlanResult result = stockLeftFixedBuyStrategy.buyPlan(context);

        log.info("{}", JSON.toJSONString(result));

    }
}
