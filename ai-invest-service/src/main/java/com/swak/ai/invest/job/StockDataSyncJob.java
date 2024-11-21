package com.swak.ai.invest.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.lib.common.log.Logs;
import com.swak.lib.common.tools.BeanTools;
import com.swak.lib.common.tools.DateTools;
import com.swak.tushar.api.BasicDataApi;
import com.swak.tushar.api.TradeDataApi;
import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.trade.TradeLine;
import com.swak.tushar.entity.trade.TradeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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

    private final TradeDataApi tradeDataApi;

    public void syncStockBasicList() {


        List<Stock> stocks = basicDataApi.stockBasic();
        batchSave(stocks, stock -> {

            StockDo stockDo = BeanTools.copy(stock, StockDo.class);
            stockDo.setCreateTime(new Date());
            stockDo.setListDate(DateTools.parseWithEmpty(stock.getListDate(), PURE_DATE_PATTERN));
            stockDo.setDeListDate(DateTools.parseWithEmpty(stock.getDeListDate(), PURE_DATE_PATTERN));
            stockDo.setCnSpell(stock.getCnspell());
            stockMapper.insert(stockDo);
        });
    }

    public void syncDaily() {

        List<StockDo> stocks = stockMapper.selectAll();

        TradeReq req = new TradeReq();
        stocks.forEach(stockDo -> {

            try {

                req.setTs_code(stockDo.getTsCode());
                List<TradeLine> dailyTrades = tradeDataApi.daily(req);
                batchSave(dailyTrades, tradeLine -> {



                });
            } catch (Exception e) {
                Logs.error("syncDaily", e, stockDo.getTsCode());
            }

            ThreadUtil.sleep(30, TimeUnit.SECONDS);

        });

    }


    private <T> void batchSave(List<T> list, Consumer<T> consumer) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }

        list.forEach(item -> {

            try {

                consumer.accept(item);
            } catch (Exception e) {
                Logs.error("batchSave", e, item);
            }

        });

    }

}
