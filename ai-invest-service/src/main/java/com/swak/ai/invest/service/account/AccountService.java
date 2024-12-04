package com.swak.ai.invest.service.account;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import com.swak.ai.invest.dao.mapper.UserInvestAccountMapper;
import com.swak.ai.invest.entity.account.AccountInitReq;
import com.swak.lib.common.tools.BeanTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/12/4
 */
@RequiredArgsConstructor
@Service
public class AccountService {


    private final UserInvestAccountMapper userInvestAccountMapper;

    public void init(AccountInitReq req) {

        UserInvestAccountDo account = userInvestAccountMapper.getByUserId(UserContext.getUserId());
        if(Objects.nonNull(account)){
            return;
        }

        account = BeanTools.copy(req, UserInvestAccountDo.class);
        account.setUserId(UserContext.getUserId());
        account.setAccountId(IdWorker.getIdStr());
        account.setAvailableAmount(req.getTotalAmount());
        account.setCreateTime(new Date());
        userInvestAccountMapper.insert(account);

    }
}
