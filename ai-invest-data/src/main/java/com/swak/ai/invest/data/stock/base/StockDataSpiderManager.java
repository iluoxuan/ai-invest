package com.swak.ai.invest.data.stock.base;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.swak.ai.invest.common.entity.MarketEnum;
import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.ai.invest.common.exception.SpiderDataException;
import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StockDataSpiderManager implements StockDataSpider {

    private final List<StockDataSpider> stockDataSpiders;


    @Override
    public List<Stock> basic(MarketEnum market) {
        return callSpider(spider -> spider.basic(market));
    }

    /**
     * 实时数据，加本地缓存
     */
    @Cached(name = "stockQuote", key = "'spider:' + #tsCode", expire = 120, cacheType = CacheType.LOCAL)
    @Override
    public StockQuote quote(String tsCode) {

        return callSpider(spider -> spider.quote(tsCode));
    }

    @Override
    public List<StockTradeLine> dailyLine(String tsCode) {
        return callSpider(spider -> spider.dailyLine(tsCode));
    }

    @Override
    public StockDailyBasic dailyBasic(String tsCode) {
        return callSpider(spider -> spider.dailyBasic(tsCode));
    }

    private <T> T callSpider(Function<StockDataSpider, T> spiderFunction) {
        for (StockDataSpider spider : stockDataSpiders) {
            try {
                return spiderFunction.apply(spider);
            } catch (Exception e) {
                log.error("spider failed", e);
            }
        }
        throw new SpiderDataException("抓取数据失败");
    }


}
