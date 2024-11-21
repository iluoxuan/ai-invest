package com.swak.ai.invest.job;

import cn.hutool.core.collection.CollectionUtil;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.lib.common.log.Logs;
import com.swak.lib.common.tools.BeanTools;
import com.swak.lib.common.tools.DateTools;
import com.swak.tushar.api.BasicDataApi;
import com.swak.tushar.entity.basic.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

/**
 * @author: ljq
 * @date: 2024/11/21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StockDataSyncJob {

    private final StockMapper stockMapper;

    private final BasicDataApi basicDataApi;

    public void syncStockBasicList() {


        List<Stock> stocks = basicDataApi.stockBasic();
        if (CollectionUtil.isEmpty(stocks)) {
            return;
        }

        stocks.forEach(stock -> {

            try {

                StockDo stockDo = BeanTools.copy(stock, StockDo.class);
                stockDo.setCreateTime(new Date());
                stockDo.setListDate(DateTools.parseWithEmpty(stock.getListDate(), PURE_DATE_PATTERN));
                stockDo.setDeListDate(DateTools.parseWithEmpty(stock.getDeListDate(), PURE_DATE_PATTERN));
                stockDo.setCnSpell(stock.getCnspell());
                stockMapper.insert(stockDo);
            } catch (Exception e) {

                Logs.error("syncStockBasicList", e, stock);
            }

        });

    }
}
