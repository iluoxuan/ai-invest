package com.swak.ai.invest.data.stock.quote;

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
