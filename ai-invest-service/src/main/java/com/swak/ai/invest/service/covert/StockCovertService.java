package com.swak.ai.invest.service.covert;

import com.swak.ai.inverst.common.entity.stock.StockQuote;
import com.swak.ai.invest.dao.domain.StockDailyBasicDo;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockDailyBasicMapper;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.ai.invest.data.stock.quote.DefaultStockQuoteSpider;
import com.swak.ai.invest.entity.account.StockBaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@RequiredArgsConstructor
@Service
public class StockCovertService {


    private final StockDailyBasicMapper stockDailyBasicMapper;
    private final DefaultStockQuoteSpider defaultStockQuoteSpider;


    public StockBaseRes covert(StockDo stock) {

        StockBaseRes stockInfo = new StockBaseRes();

        stockInfo.setTsCode(stock.getTsCode());
        StockDailyBasicDo dailyBasic = stockDailyBasicMapper.getByTsCode(stock.getTsCode());
        stockInfo.setTotalMv(dailyBasic.getTotalMv());
        stockInfo.setStockCnName(stock.getName());

        // 实时股价
        StockQuote stockQuote = defaultStockQuoteSpider.spider(stock.getTsCode());
        if (Objects.nonNull(stockQuote)) {
            stockInfo.setPe(stockQuote.getPe());
            stockInfo.setPrice(stockQuote.getCurrentPrice());
        }
        return stockInfo;
    }
}
