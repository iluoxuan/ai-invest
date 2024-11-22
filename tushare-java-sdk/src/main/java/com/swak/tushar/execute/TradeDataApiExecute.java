package com.swak.tushar.execute;

import com.swak.tushar.api.TradeDataApi;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.trade.StockDailyBasic;
import com.swak.tushar.entity.trade.StockTradeLine;
import com.swak.tushar.entity.trade.TradeReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/21
 */
@RequiredArgsConstructor
@Service
public class TradeDataApiExecute implements TradeDataApi {

    private final DefaultApiExecute defaultApiExecute;

    @Override
    public List<StockTradeLine> daily(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.daily, req, StockTradeLine.class);
    }

    @Override
    public List<StockTradeLine> weekly(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.weekly, req, StockTradeLine.class);
    }

    @Override
    public List<StockTradeLine> monthly(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.monthly, req, StockTradeLine.class);
    }

    @Override
    public List<StockDailyBasic> stockDailyBasic(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.daily_basic, req, StockDailyBasic.class);
    }

}
