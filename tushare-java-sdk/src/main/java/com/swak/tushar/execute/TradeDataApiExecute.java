package com.swak.tushar.execute;

import com.swak.tushar.api.TradeDataApi;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.trade.TradeLine;
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
    public List<TradeLine> daily(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.daily, req, TradeLine.class);
    }

    @Override
    public List<TradeLine> weekly(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.weekly, req, TradeLine.class);
    }

    @Override
    public List<TradeLine> monthly(TradeReq req) {
        return defaultApiExecute.execute(ApiNameEnum.monthly, req, TradeLine.class);
    }


}
