package com.swak.ai.invest.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.thread.ThreadUtil;
import com.swak.ai.invest.common.entity.stock.StockTsCode;
import com.swak.ai.invest.dao.domain.StockDailyBasicDo;
import com.swak.ai.invest.dao.domain.StockDailyLineDo;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockDailyBasicMapper;
import com.swak.ai.invest.dao.mapper.StockDailyLineMapper;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.ai.invest.data.context.SpiderContext;
import com.swak.ai.invest.data.stock.daily.DefaultStockDailyBasicSpider;
import com.swak.ai.invest.data.xueqiu.XueQiuStockQuote;
import com.swak.lib.common.log.Logs;
import com.swak.lib.common.tools.BeanTools;
import com.swak.lib.common.tools.DateTools;
import com.swak.tushar.api.BasicDataApi;
import com.swak.tushar.api.TradeDataApi;
import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;
import com.swak.tushar.entity.trade.TradeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN;
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

    private final StockDailyLineMapper stockDailyLineMapper;

    private final StockDailyBasicMapper stockDailyBasicMapper;

    private final DefaultStockDailyBasicSpider stockDailyBasicSpider;

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

    public void syncDailyByTsCode(String tsCode) {

        try {
            TradeReq req = new TradeReq();
            req.setTs_code(tsCode);

            DateTime now = DateTime.now();
            // 判断今天是否周末
            if (DateTools.isWeekend(now)) {
                now = DateTools.getPreviousFriday(now);
            }
            StockDailyLineDo exist = stockDailyLineMapper.getBy(tsCode, DateTools.parseWithEmpty(now.toDateStr(), NORM_DATE_PATTERN));
            if (Objects.isNull(exist)) {
                syncDailyLIne(tsCode, req);
            }

            StockDailyBasicDo dailyBasic = stockDailyBasicMapper.getByTsCode(tsCode);
            if (Objects.nonNull(dailyBasic)) {
                return;
            }

            // 通过雪球同步
            StockDailyBasic stockDailyBasic = stockDailyBasicSpider.spider(tsCode);
            if (Objects.nonNull(stockDailyBasic)) {
                dailyBasic = BeanTools.copy(stockDailyBasic, StockDailyBasicDo.class);
                Date tradeDate = DateTools.parseWithEmpty(stockDailyBasic.getTradeDate(), NORM_DATE_PATTERN);
                dailyBasic.setTradeDate(tradeDate);
                dailyBasic.setCreateTime(new Date());
                dailyBasic.setUpdateTime(new Date());
                stockDailyBasicMapper.insert(dailyBasic);

            }

            // 保存股票基本信息 测试先用
            StockDo stockDo = stockMapper.getByTsCode(tsCode);
            if (Objects.isNull(stockDo)) {
                stockDo = new StockDo();
                XueQiuStockQuote quote = SpiderContext.getInstance().getXueQiuStockQuote();
                StockTsCode stockTsCode = StockTsCode.create(tsCode);
                stockDo.setCreateTime(new Date());
                stockDo.setTsCode(tsCode);
                stockDo.setMarket(stockTsCode.getMarket());
                stockDo.setSymbol(stockTsCode.getSymbol());
                stockDo.setName(quote.getName());
                stockDo.setExchange(quote.getExchange());
                stockMapper.insert(stockDo);
            }

        } catch (Exception e) {
            Logs.error("syncDailyByTsCode", e, tsCode);
        }
    }

    private void syncDailyLIne(String tsCode, TradeReq req) {
        List<StockTradeLine> dailyTrades = tradeDataApi.daily(req);
        batchSave(dailyTrades, stockTradeLine -> {
            try {

                // 判断是否已经同步
                Date tradeDate = DateTools.parseWithEmpty(stockTradeLine.getTradeDate(), PURE_DATE_PATTERN);
                StockDailyLineDo dailyLine = stockDailyLineMapper.getBy(tsCode, tradeDate);
                if (Objects.nonNull(dailyLine)) {
                    return;
                }
                StockDailyLineDo dailyLineDo = BeanTools.copy(stockTradeLine, StockDailyLineDo.class);
                dailyLineDo.setTradeDate(tradeDate);
                dailyLineDo.setCreateTime(new Date());
                dailyLineDo.setUpdateTime(new Date());
                stockDailyLineMapper.insert(dailyLineDo);
            } catch (Exception e) {
                log.error("syncDailyByTsCode batchSave tradeLine={} error", stockTradeLine, e);
            }

        });
    }

    public void syncDaily() {

        List<StockDo> stocks = stockMapper.selectAll();


        stocks.forEach(stockDo -> {

            syncDailyByTsCode(stockDo.getTsCode());

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
