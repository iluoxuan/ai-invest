package com.swak.ai.invest.service.data;

import cn.hutool.core.util.NumberUtil;
import com.alicp.jetcache.anno.Cached;
import org.springframework.stereotype.Service;

/**
 * 实时股价数据
 * <p>
 * 只需要知道当前实时股价 就算
 * https://stock.xueqiu.com/v5/stock/quote.json?symbol=09988&extend=detail
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@Service
public class StockQuoteDataService {

    @Cached(name = "stockQuote", key = "#tsCode", expire = 60)
    public StockQuote getStockQuote(String tsCode) {

        // 爬取对于的实时股价 TODO
        StockQuote stockQuote = new StockQuote();
        stockQuote.setTsCode(tsCode);
        stockQuote.setCurrentPrice(NumberUtil.toBigDecimal(80));
        return stockQuote;
    }

}
