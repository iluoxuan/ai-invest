package com.swak.ai.invest.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
public class JsoupTest {


    public static void main(String[] args) throws IOException {

        String path = "/v5/stock/quote.json";

        String url = "https://xueqiu.com/?md5__1038=QqGxcDnDyiitnD050IYYKcDYq8QvKDB7CCoD";

        url = "https://xueqiu.com/hq";

        Connection connection = Jsoup.connect(url);
        connection.get();

        Map<String, String> cookies = connection.response().cookies();

        // 获取实时股价

        url = "https://stock.xueqiu.com/v5/stock/quote.json?symbol=09988&extend=detail";
        // 发送 GET 请求
        Connection.Response response = Jsoup.connect(url).cookies(cookies)
                .ignoreContentType(true) // 忽略内容类型，允许接收非 HTML 内容
                .method(Connection.Method.GET)
                .execute();

        System.out.println(response.body());

    }
}
