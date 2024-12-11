package com.swak.ai.invest.data.stock.quote;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson2.JSONPath;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.ai.invest.data.config.SpiderUrl;
import com.swak.ai.invest.data.third.XueQiuPcToken;
import com.swak.ai.invest.data.third.XueQiuPcTokenHandler;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@RequiredArgsConstructor
@Service
public class XueQuiSpiderManager implements StockQuoteSpider {

    private final XueQiuPcTokenHandler xueQiuPcTokenHandler;


    @Cached(name = "data:xueQiu:quote", key = "#tsCode", expire = 60, cacheType = CacheType.LOCAL)
    @Override
    public StockQuote spider(String tsCode) throws Exception {

        XueQiuPcToken xueQiuPcToken = xueQiuPcTokenHandler.getToken();

        String symbol = XueQiuTools.symbol(tsCode);

        // 抓取实时数据
        String url = UriComponentsBuilder.fromHttpUrl(SpiderUrl.xueQiuDomain).path(SpiderUrl.quotePath)
                .queryParam("symbol", symbol)
                .queryParam("extend", "detail").build().toUriString();
        String quoteJson = Jsoup.connect(url).cookies(xueQiuPcToken.getCookies())
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute().body();
        Object currentPrice = JSONPath.of("$.data.quote.current").eval(quoteJson);
        Object pe = JSONPath.of("$.data.quote.pe_ttm").eval(quoteJson);
        Object high52w = JSONPath.of("$.data.quote.high52w").eval(quoteJson);
        Object low52w = JSONPath.of("$.data.quote.low52w").eval(quoteJson);
        Object percent = JSONPath.of("$.data.quote.percent").eval(quoteJson);
        Assert.notNull(currentPrice, "currentPrice is null");
        Assert.notNull(pe, "pe is null");

        // 获取价格
        StockQuote stockQuote = new StockQuote();
        stockQuote.setTsCode(tsCode);
        stockQuote.setCurrentPrice(to(currentPrice));
        stockQuote.setPe(to(pe));
        stockQuote.setPercent(to(percent));
        stockQuote.setLow52w(to(low52w));
        stockQuote.setHigh52w(to(high52w));
        return stockQuote;
    }

    public BigDecimal to(Object value) {
        return NumberUtil.toBigDecimal(String.valueOf(value));
    }
}
