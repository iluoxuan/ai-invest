package com.swak.ai.invest.service.covert;

import com.swak.ai.invest.common.entity.stock.StockLow;
import com.swak.ai.invest.common.entity.stock.StockQuote;
import com.swak.ai.invest.dao.domain.StockDailyBasicDo;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.mapper.StockDailyBasicMapper;
import com.swak.ai.invest.data.stock.base.StockDataSpiderManager;
import com.swak.ai.invest.service.stock.StockInfo;
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
    private final StockDataSpiderManager stockDataSpiderManager;


    public StockInfo covert(StockDo stock) {

        return covert(stock, null);
    }

    public StockInfo covert(StockDo stock, StockLow stockLow) {

        StockInfo stockInfo = new StockInfo();

        stockInfo.setTsCode(stock.getTsCode());
        stockInfo.setSymbol(stock.getSymbol());
        StockDailyBasicDo dailyBasic = stockDailyBasicMapper.getByTsCode(stock.getTsCode());
        stockInfo.setTotalMv(dailyBasic.getTotalMv());
        stockInfo.setCnName(stock.getName());

        // 实时股价
        StockQuote stockQuote = stockDataSpiderManager.quote(stock.getTsCode());
        if (Objects.nonNull(stockQuote)) {
            stockInfo.setPe(stockQuote.getPe());
            stockInfo.setPrice(stockQuote.getCurrentPrice());
        }

        if (Objects.nonNull(stockLow)) {
            stockInfo.setLow1y(stockLow.getLow1y());
            stockInfo.setLow10y(stockLow.getLow10y());
        }

        return stockInfo;
    }
}
