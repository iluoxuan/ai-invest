package com.swak.ai.invest.entity.account;

import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import lombok.Data;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/12/5
 */
@Data
public class AccountInfoRes {


    private UserInvestAccountDo account;

    private List<AccountStockPositionDo> stockPosition;
}
