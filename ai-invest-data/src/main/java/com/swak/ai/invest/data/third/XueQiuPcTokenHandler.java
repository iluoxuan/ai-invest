package com.swak.ai.invest.data.third;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

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

    public static String xueQiuHqUrl = "https://xueqiu.com/hq";

    private final WebDriver webDriver;


    @Cached(name = "data:getToken", key = "xueQiu", expire = 60, timeUnit = TimeUnit.MINUTES, cacheType = CacheType.LOCAL)
    public XueQiuPcToken getToken() throws Exception {

        try {
            XueQiuPcToken xueQiuPcToken = new XueQiuPcToken();

            Connection connection = Jsoup.connect(xueQiuHqUrl);
            connection.get();

            Map<String, String> cookies = connection.response().cookies();

            xueQiuPcToken.setCookies(cookies);

            return xueQiuPcToken;
        } finally {
            webDriver.quit();
        }
    }

}
