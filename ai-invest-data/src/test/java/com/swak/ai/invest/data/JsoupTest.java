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

        String url = "https://xueqiu.com/?md5__1038=QqGxcDnDyiitnD05o4%2Br%3DDRGnDCY1OOaF4D";

        Connection connection = Jsoup.connect(url);
        connection.get();

        Map<String, String> cookies = connection.response().cookies();

    }
}
