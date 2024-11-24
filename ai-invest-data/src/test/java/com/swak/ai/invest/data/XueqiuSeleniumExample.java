package com.swak.ai.invest.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XueqiuSeleniumExample {


    public static void main(String[] args) throws IOException {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "D:\\ljq\\work\\config\\chromedriver-win64\\chromedriver.exe");

        // 创建 ChromeOptions 对象
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);


        // 创建 WebDriver 对象
        WebDriver driver = new ChromeDriver(options);

        try {
            // 访问目标 URL
            driver.get("https://xueqiu.com/hq");
            String currentUrl = driver.getCurrentUrl();

            Set<Cookie> cookies = driver.manage().getCookies();


            Map<String, String> cookiesMap = new HashMap<>();
            cookies.forEach(cookie -> {
                cookiesMap.put(cookie.getName(), cookie.getValue());
            });
            String url = "https://stock.xueqiu.com/v5/stock/quote.json?symbol=09988&extend=detail";
            Connection.Response response = Jsoup.connect(url).cookies(cookiesMap)
                    .ignoreContentType(true) // 忽略内容类型，允许接收非 HTML 内容
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println(response.body());


            System.out.println(currentUrl);


        } finally {
            // 关闭浏览器
            driver.close();
            driver.quit();
        }
    }
}
