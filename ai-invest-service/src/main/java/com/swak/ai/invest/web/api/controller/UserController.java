package com.swak.ai.invest.web.api.controller;

import com.swak.ai.invest.entity.user.UserInfoRes;
import com.swak.ai.invest.service.user.UserService;
import com.swak.lib.client.entity.ApiRes;
import com.swak.lib.common.annotion.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ljq
 * @date: 2024/11/22
 */
@LogTrace
@Api(tags = "用户信息")
@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;

    /**
     * 初始化的时候调用
     */
    @ApiOperation("用户信息")
    @PostMapping("/info")
    public ApiRes<UserInfoRes> info() {
        return ApiRes.success(userService.info());
    }


}
