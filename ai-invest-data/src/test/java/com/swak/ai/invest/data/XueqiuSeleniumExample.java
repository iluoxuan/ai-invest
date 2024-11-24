package com.swak.ai.invest.data;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
            driver.get("https://xueqiu.com/");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            String currentUrl = driver.getCurrentUrl();

            //  driver.get(currentUrl);

            Set<Cookie> cookies = driver.manage().getCookies();
            driver.get(currentUrl);

            cookies = driver.manage().getCookies();


            System.out.println(currentUrl);


        } finally {
            // 关闭浏览器
            driver.close();
            driver.quit();
        }
    }
}
