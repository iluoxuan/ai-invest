package com.swak.ai.invest.service.buy;

import cn.hutool.core.util.NumberUtil;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.entity.InvestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 左侧固定比例加仓策略
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@RequiredArgsConstructor
@Service
public class StockLeftFixedBuyStrategy implements StockBuyStrategyPlan {


    private final AccountStockPositionMapper accountStockPositionMapper;


    @Override
    public StockBuyPlanResult buyPlan(StockBuyContext context) {

        // 没跌1%的仓位 左侧加仓计划
        AccountStockPositionDo stockPosition = accountStockPositionMapper.getBy(context.getAccountId(), context.getTsCode());
        if (Objects.isNull(stockPosition)) {
            // 初始化持仓账户
            stockPosition.setAccountId(context.getAccountId());
            stockPosition.setTsCode(context.getTsCode());
            stockPosition.setQuantity(0);
            stockPosition.setPlanAmount(NumberUtil.toBigDecimal(InvestConstants.defaultPlanAmount));
            stockPosition.setCreateTime(new Date());
            accountStockPositionMapper.insert(stockPosition);
        }





        return null;
    }

    @Override
    public StockBuyPlanName name() {
        return StockBuyPlanName.leftBuy;
    }
}
