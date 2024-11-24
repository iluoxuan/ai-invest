package com.swak.ai.invest.data.third;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public static String xueQiuUrl = "https://xueqiu.com";

    private final WebDriver webDriver;


    @Cached(name = "data:getToken", key = "xueQiu", expire = 60, timeUnit = TimeUnit.MINUTES, cacheType = CacheType.LOCAL)
    public XueQiuPcToken getToken() throws Exception {

        XueQiuPcToken xueQiuPcToken = new XueQiuPcToken();

        webDriver.get(xueQiuUrl);
        // 打开首页获取跳转的url
        String currentUrl = webDriver.getCurrentUrl();
        // 获取？ 后面的参数
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromHttpUrl(currentUrl)
                .build()
                .getQueryParams();
        String md5Flag = queryParams.getFirst("md5__1038");
        log.info("getToken md5__1038={}", md5Flag);
        xueQiuPcToken.setMd5Flag(md5Flag);


        Connection connection = Jsoup.connect(currentUrl);
        connection.get();
        Map<String, String> cookies = connection.response().cookies();
        xueQiuPcToken.setCookies(cookies);
        return xueQiuPcToken;
    }

}
