package com.swak.ai.invest.service.user;

import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.dao.domain.UserDo;
import com.swak.ai.invest.dao.domain.UserInvestAccountDo;
import com.swak.ai.invest.dao.mapper.UserInvestAccountMapper;
import com.swak.ai.invest.dao.mapper.UserMapper;
import com.swak.ai.invest.entity.user.UserInfoRes;
import com.swak.lib.common.tools.AssertTools;
import com.swak.lib.common.tools.BeanTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/12/6
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserInvestAccountMapper userInvestAccountMapper;

    public UserInfoRes info() {

        String userId = UserContext.userId();
        UserDo userDo = userMapper.selectById(userId);
        AssertTools.notNull(userDo, "用户不存在");
        UserInfoRes infoRes = BeanTools.copy(userDo, UserInfoRes.class);
        UserInvestAccountDo account = userInvestAccountMapper.getByUserId(userId);
        infoRes.setHadInitAccount(Objects.nonNull(account) ? true : false);
        return infoRes;
    }
}
