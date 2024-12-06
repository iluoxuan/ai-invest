package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.invest.dao.domain.UserDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-12-06
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDo> {

    default UserDo getByWxId(String wxId) {
        return selectOne(Wrappers.lambdaQuery(UserDo.class)
                .eq(UserDo::getWxId, wxId)
        );
    }
}
