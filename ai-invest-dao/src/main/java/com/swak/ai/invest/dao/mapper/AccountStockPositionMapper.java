package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.invest.dao.domain.AccountStockPositionDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 账户持仓 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Mapper
public interface AccountStockPositionMapper extends BaseMapper<AccountStockPositionDo> {

    default AccountStockPositionDo getBy(String accountId, String tsCode) {
        return selectOne(
                Wrappers.lambdaQuery(AccountStockPositionDo.class)
                        .eq(AccountStockPositionDo::getAccountId, accountId)
                        .eq(AccountStockPositionDo::getTsCode, tsCode)
        );
    }
}
