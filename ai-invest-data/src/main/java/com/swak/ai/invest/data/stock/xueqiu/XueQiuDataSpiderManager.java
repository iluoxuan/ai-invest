package com.swak.ai.invest.data.stock.xueqiu;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPath;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.ai.invest.common.exception.SpiderDataException;
import com.swak.ai.invest.data.config.SpiderUrl;
import com.swak.ai.invest.data.stock.base.StockDataSpider;
import com.swak.lib.common.tools.AssertTools;
import com.swak.lib.common.tools.BeanTools;
import com.swak.lib.common.tools.DateTools;
import com.swak.lib.common.tools.StringTools;
import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class XueQiuDataSpiderManager implements StockDataSpider {

    private final XueQuiStockManager xueQuiStockManager;
    private final XueQiuPcTokenHandler xueQiuPcTokenHandler;

    @Cached(name = "xueQiu", key = "'quote:'+ #tsCode", expire = 60, cacheType = CacheType.LOCAL)
    @Override
    public StockQuote quote(String tsCode) {

        XueQiuStockQuote quote = xueQuiStockManager.getStockQuote(tsCode);

        // 获取价格
        StockQuote stockQuote = new StockQuote();
        stockQuote.setTsCode(tsCode);
        stockQuote.setCurrentPrice(quote.getCurrent());
        stockQuote.setPe(quote.getPeTtm());
        stockQuote.setPercent(quote.getPercent());
        stockQuote.setLow52w(quote.getLow52w());
        stockQuote.setHigh52w(quote.getHigh52w());
        return stockQuote;
    }

    @Cached(name = "xueQiu", key = "'dailyLine:'+ #tsCode", expire = 60 * 60, cacheType = CacheType.LOCAL)
    @Override
    public List<StockTradeLine> dailyLine(String tsCode) {

        try {

            XueQiuPcToken xueQiuPcToken = xueQiuPcTokenHandler.getToken();
            String symbol = XueQiuTools.symbol(tsCode);
            // 抓取实时数据
            String url = UriComponentsBuilder.fromHttpUrl(SpiderUrl.xueQiuDomain).path(SpiderUrl.klinePath)
                    .queryParam("symbol", symbol)
                    .queryParam("extend", "detail")
                    .queryParam("begin", System.currentTimeMillis())
                    .queryParam("period", "day")
                    .queryParam("type", "before")
                    .queryParam("count", -284)
                    .queryParam("indicator", "kline,pe,pb,ps,pcf,market_capital,agt,ggt,balance")
                    .build().toUriString();

            String klineJson = Jsoup.connect(url).cookies(xueQiuPcToken.getCookies())
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute().body();
            if (StringTools.isBlank(klineJson)) {
                return Collections.emptyList();
            }
            Object dataObj = JSONPath.eval(klineJson, "$.data");
            XueQiuKlineResponseData apiResponseData = JSON.parseObject(dataObj.toString(), XueQiuKlineResponseData.class);
            List<XueQiuKline> xueQiuKlineList = apiResponseData.create(XueQiuKline.class);

            return BeanTools.copyList(xueQiuKlineList, xueQiuKline -> {

                StockTradeLine tradeLine = BeanTools.copy(xueQiuKline, StockTradeLine.class);
                tradeLine.setTradeDate(DateTime.of(xueQiuKline.getTimestamp()).toString(PURE_DATE_PATTERN));
                tradeLine.setPctChg(xueQiuKline.getChg());
                tradeLine.setTsCode(tsCode);
                return tradeLine;

            });
        } catch (Exception e) {

            throw new SpiderDataException(e);
        }

    }

    @Override
    public StockDailyBasic dailyBasic(String tsCode) {

        XueQiuStockQuote quote = xueQuiStockManager.getStockQuote(tsCode);
        AssertTools.notNull(quote, "获取当日股票信息为空");
        StockDailyBasic dailyBasic = BeanTools.copy(quote, StockDailyBasic.class);
        dailyBasic.setTsCode(tsCode);
        dailyBasic.setPe(quote.getPeTtm());
        dailyBasic.setPs(quote.getEps());
        dailyBasic.setTotalMv(quote.getMarketCapital());
        dailyBasic.setOpen(dailyBasic.getOpen());

        // 时间戳转日期
        DateTime dateTime = DateTools.date(quote.getTimestamp());
        dailyBasic.setTradeDate(dateTime.toDateStr());
        dailyBasic.setClose(quote.getCurrent());
        return dailyBasic;
    }
}