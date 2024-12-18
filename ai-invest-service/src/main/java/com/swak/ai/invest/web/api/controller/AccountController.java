package com.swak.ai.invest.web.api.controller;

import com.swak.ai.invest.context.UserContext;
import com.swak.ai.invest.entity.account.AccountInfoRes;
import com.swak.ai.invest.entity.account.AccountInitReq;
import com.swak.ai.invest.service.account.AccountService;
import com.swak.lib.client.entity.ApiRes;
import com.swak.lib.common.annotion.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: ljq
 * @date: 2024/12/4
 */
@LogTrace
@Api(tags = "账户")
@RequestMapping("/api/account")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @ApiOperation("初始化账户信息")
    @PostMapping("/init")
    public ApiRes<Void> init(@Valid @RequestBody AccountInitReq req) {
        accountService.init(req);
        return ApiRes.success();
    }

    @ApiOperation("账户信息")
    @PostMapping("/info")
    public ApiRes<AccountInfoRes> info() {
        return ApiRes.success(accountService.info());
    }
}
