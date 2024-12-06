package com.swak.ai.invest.context;

import lombok.Getter;
import lombok.Setter;

/**
 * 当前用户上下文
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@Setter
@Getter
public class UserContext {

    private String userId;

    private String accountId;

    private static final ThreadLocal<UserContext> context = ThreadLocal.withInitial(UserContext::new);

    public static UserContext getInstance() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

    public static String userId() {
        return getInstance().getUserId();
    }

    public static String accountId() {
        return getInstance().getAccountId();
    }
}
