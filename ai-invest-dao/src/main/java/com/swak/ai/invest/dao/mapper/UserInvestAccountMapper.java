package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户投资账户 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-12-04
 */
@Mapper
public interface UserInvestAccountMapper extends BaseMapper<UserInvestAccountDo> {

    default UserInvestAccountDo getByUserId(String userId) {
        return selectOne(
                Wrappers.lambdaQuery(UserInvestAccountDo.class).eq(UserInvestAccountDo::getUserId, userId));
    }
}
