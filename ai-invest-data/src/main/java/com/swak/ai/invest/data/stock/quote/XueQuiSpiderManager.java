package com.swak.ai.invest.data.stock.quote;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson2.JSONPath;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.swak.ai.inverst.common.entity.stock.StockQuote;
import com.swak.ai.invest.data.config.SpiderUrl;
import com.swak.ai.invest.data.third.XueQiuPcToken;
import com.swak.ai.invest.data.third.XueQiuPcTokenHandler;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@RequiredArgsConstructor
@Service
public class XueQuiSpiderManager implements StockQuoteSpider {

    private final XueQiuPcTokenHandler xueQiuPcTokenHandler;

    private String path = "/v5/stock/quote.json";

    @Cached(name = "data:xueQiu:quote", key = "#tsCode", expire = 60, cacheType = CacheType.LOCAL)
    @Override
    public StockQuote spider(String tsCode) throws Exception {

        XueQiuPcToken xueQiuPcToken = xueQiuPcTokenHandler.getToken();

        String symbol = XueQiuTools.symbol(tsCode);

        // 抓取实时数据
        String url = UriComponentsBuilder.fromHttpUrl(SpiderUrl.xueQiuDomain).path(path)
                .queryParam("symbol", symbol)
                .queryParam("extend", "detail").build().toUriString();
        String quoteJson = Jsoup.connect(url).cookies(xueQiuPcToken.getCookies())
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute().body();
        Object currentPrice = JSONPath.of("$.quote.current").eval(quoteJson);
        Object pe = JSONPath.of("$.quote.pe_ttm").eval(quoteJson);
        Assert.notNull(currentPrice, "currentPrice is null");
        Assert.notNull(pe, "pe is null");

        // 获取价格
        StockQuote stockQuote = new StockQuote();
        stockQuote.setTsCode(tsCode);
        stockQuote.setCurrentPrice(NumberUtil.toBigDecimal(String.valueOf(currentPrice)));
        stockQuote.setPe(NumberUtil.toBigDecimal(String.valueOf(pe)));
        return stockQuote;
    }
}
