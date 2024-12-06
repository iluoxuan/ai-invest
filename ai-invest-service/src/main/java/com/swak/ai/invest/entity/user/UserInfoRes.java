package com.swak.ai.invest.entity.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: ljq
 * @date: 2024/12/6
 */
@Data
public class UserInfoRes {

    private String name;

    private String mobile;

    private boolean hadInitAccount;

    private Date createTime;

    private List<String> userTags;

}
