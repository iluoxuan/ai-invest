package com.swak.ai.invest.service.data;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONPath;
import com.swak.ai.inverst.common.constants.HttpReqConstants;
import com.swak.ai.inverst.common.entity.stock.StockQuote;
import com.swak.lib.common.httpclient.SwakHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 实时股价数据
 * <p>
 * 只需要知道当前实时股价 就算
 * https://stock.xueqiu.com/v5/stock/quote.json?symbol=09988&extend=detail
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@Slf4j
@Service
public class StockQuoteDataService {

    //@Cached(name = "stockQuote", key = "#tsCode", expire = 60, cacheType = CacheType.LOCAL)
    public Optional<StockQuote> getStockQuote(String tsCode) {

        try {
            String result = SwakHttpClient.create(HttpReqNameEnum.xueqiu)
                    .path(HttpReqConstants.Path.xueqiuQuote).addParam("symbol", tsCode).addParam("extend", "detail")
                    .get();
            if (StrUtil.isBlank(result)) {
                return Optional.empty();
            }
            StockQuote stockQuote = new StockQuote();
            stockQuote.setTsCode(tsCode);
            String currentPrice = JSONPath.of("$.quote.current").eval(result).toString();
            stockQuote.setCurrentPrice(NumberUtil.toBigDecimal(currentPrice));
            String pe = JSONPath.of("$.quote.pe_ttm").eval(result).toString();
            stockQuote.setPe(NumberUtil.toBigDecimal(pe));
            return Optional.of(stockQuote);

        } catch (Exception e) {
            log.error("getStockQuote tsCode={}", tsCode, e);
        }
        return Optional.empty();
    }

}
