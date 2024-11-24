package com.swak.ai.invest.data.third;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 处理雪球 token 和url
 *
 * @author: ljq
 * @date: 2024/11/24
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XueQiuPcTokenHandler {

    private static String xueQiuUrl = "https://xueqiu.com";

    private final WebDriver webDriver;

    public XueQiuPcToken getToken() {

        webDriver.get(xueQiuUrl);
        // 打开首页获取跳转的url
        String currentUrl = webDriver.getCurrentUrl();
        // 获取？ 后面的参数
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromHttpUrl(currentUrl)
                .build()
                .getQueryParams();
        String md5Flag = queryParams.getFirst("md5__1038");
        log.info("getToken md5__1038={}", md5Flag);




        return new XueQiuPcToken();
    }

}
