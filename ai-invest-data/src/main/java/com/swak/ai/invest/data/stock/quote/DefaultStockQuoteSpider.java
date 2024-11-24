package com.swak.ai.invest.data.stock.quote;

import com.swak.ai.inverst.common.constants.HttpReqConstants;
import com.swak.ai.inverst.common.entity.stock.StockQuote;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.CookieStore;
import java.util.Map;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Service
public class DefaultStockQuoteSpider implements StockQuoteSpider {

    @Override
    public StockQuote spider(String tsCode) {


        CookieStore cookieStore = Jsoup.connect("https://xueqiu.com/").cookieStore();


        return null;
    }

    public static void main(String[] args) throws IOException {


        // 发送 GET 请求
        Connection.Response response = Jsoup.connect("https://xueqiu.com/")
                .method(Connection.Method.GET)
                .header("User-Agent", HttpReqConstants.SpiderHeader.userAgent)
                .execute();

        // 获取响应头中的 Cookie
        Map<String, String> cookies = response.cookies();

        // 打印 Cookie
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
