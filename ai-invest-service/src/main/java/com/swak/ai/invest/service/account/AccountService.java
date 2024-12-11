package com.swak.ai.invest.service.account;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import com.swak.ai.invest.dao.mapper.AccountStockPositionMapper;
import com.swak.ai.invest.dao.mapper.StockMapper;
import com.swak.ai.invest.dao.mapper.UserInvestAccountMapper;
import com.swak.ai.invest.entity.account.AccountInfoRes;
import com.swak.ai.invest.entity.account.AccountInitReq;
import com.swak.ai.invest.service.stock.StockInfo;
import com.swak.ai.invest.service.covert.StockCovertService;
import com.swak.lib.common.tools.AssertTools;
import com.swak.lib.common.tools.BeanTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/12/4
 */
@RequiredArgsConstructor
@Service
public class AccountService {


    private final UserInvestAccountMapper userInvestAccountMapper;
    private final AccountStockPositionMapper accountStockPositionMapper;
    private final StockMapper stockMapper;
    private final StockCovertService stockCovertService;

    public void init(AccountInitReq req) {

        UserInvestAccountDo account = userInvestAccountMapper.getByUserId(UserContext.getInstance().getUserId());
        if (Objects.nonNull(account)) {
            return;
        }

        account = BeanTools.copy(req, UserInvestAccountDo.class);
        account.setUserId(UserContext.getInstance().getUserId());
        account.setAccountId(IdWorker.getIdStr());
        account.setAvailableAmount(req.getTotalAmount());
        account.setCreateTime(new Date());
        userInvestAccountMapper.insert(account);

    }

    public AccountInfoRes info() {

        UserInvestAccountDo account = userInvestAccountMapper.getByUserId(UserContext.getInstance().getUserId());
        AssertTools.notNull(account, "账户不存在");

        AccountInfoRes infoRes = new AccountInfoRes();
        infoRes.setAccount(account);
        // 持仓数量最多不超过30个
        List<AccountStockPositionDo> positionList = accountStockPositionMapper.getByAccountId(account.getAccountId());
        infoRes.setStockList(BeanTools.copyList(positionList, position -> {
            // 加缓存
            StockDo stock = stockMapper.getByTsCode(position.getTsCode());

            StockInfo stockInfo = stockCovertService.covert(stock);
            return stockInfo;

        }));

        return infoRes;
    }
}
