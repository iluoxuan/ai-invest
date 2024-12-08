package com.swak.ai.invest.entity.account;

import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import com.swak.ai.invest.tools.BigDecimalTools;
import lombok.Data;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/12/5
 */
@Data
public class AccountInfoRes {


    private AccountInvestInfoRes account;

    private List<StockBaseRes> stockList;

    public void setAccount(UserInvestAccountDo account) {
        this.account = new AccountInvestInfoRes();
        this.account.setPlanAmount(BigDecimalTools.format(account.getPlanAmount()));
        this.account.setTotalAmount(BigDecimalTools.format(account.getTotalAmount()));
        this.account.setMonthlyExpenses(BigDecimalTools.format(account.getMonthlyExpenses()));
        this.account.setMonthlyIncome(BigDecimalTools.format(account.getMonthlyIncome()));
        this.account.setActualAmount(BigDecimalTools.format(account.getActualAmount()));
        this.account.setUsedAmount(BigDecimalTools.format(account.getUsedAmount()));
        this.account.setFrozenAmount(BigDecimalTools.format(account.getFrozenAmount()));
        this.account.setAvailableAmount(BigDecimalTools.format(account.getAvailableAmount()));
    }
}
