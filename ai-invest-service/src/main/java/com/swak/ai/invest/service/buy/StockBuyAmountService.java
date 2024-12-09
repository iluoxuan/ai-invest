package com.swak.ai.invest.service.buy;

import com.swak.lib.common.number.BigNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 股票买入金额计算
 *
 * @author: ljq
 * @date: 2024/12/9
 */
@RequiredArgsConstructor
@Service
public class StockBuyAmountService {

    /**
     * 按加仓资金额度划分
     * 如： 100w以内  200以内
     *
     * @param totalAmount
     * @return
     */
    public BigDecimal buyAmountRule(BigDecimal totalAmount) {

        return BigNumber.of(totalAmount).divScale2(5).getValue();
    }
}
