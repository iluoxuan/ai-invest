package com.swak.ai.invest.entity.buy;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCouponInfo;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.entity.account.AccountInvestInfoRes;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 买入计划结果
 *
 * @author: ljq
 * @date: 2024/11/22
 */
@Data
public class StockBuyPlanResult {

    private AccountInvestInfoRes account;
    private AccountStockPositionDo position;

    private List<StockBuyPlanUnit> buyPlanUnits = new ArrayList<>();

    private int buyCnt;
}
