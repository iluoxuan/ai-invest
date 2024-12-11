package com.swak.ai.invest.data.stock.quote;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.lib.client.exception.SwakBizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultStockQuoteSpider implements StockQuoteSpider {

    private final List<StockQuoteSpider> stockQuoteSpiders;


    /**
     * 实时数据，加本地缓存
     */
    @Cached(name = "stockQuote", key = "'spider:' + #tsCode", expire = 120, cacheType = CacheType.LOCAL)
    @Override
    public StockQuote spider(String tsCode) {

        for (StockQuoteSpider spider : stockQuoteSpiders) {

            try {

                return spider.spider(tsCode);
            } catch (Exception e) {
                log.error("spider tsCode={}", tsCode, e);
            }
        }

        throw SwakBizException.argumentError("实时数据获取失败");
    }


}
