package com.swak.ai.invest.web.api.controller;

import com.swak.lib.common.annotion.LogTrace;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用信息
 *
 * @author: ljq
 * @date: 2024/12/6
 */
@LogTrace
@Api(tags = "应用信息")
@RequestMapping("/api/app")
@RequiredArgsConstructor
@RestController
public class AppController {


}
