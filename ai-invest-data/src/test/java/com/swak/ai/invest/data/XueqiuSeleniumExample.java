package com.swak.ai.invest.data;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;

public class XueqiuSeleniumExample {

    public static void main(String[] args) throws IOException {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "D:\\ljq\\work\\config\\chromedriver-win64\\chromedriver.exe");

        // 创建 ChromeOptions 对象
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式
        options.addArguments("--remote-allow-origins=*");
        // 创建 ChromeOptions 对象
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);


        // 创建 WebDriver 对象
        WebDriver driver = new ChromeDriver(options);

        try {
            // 访问目标 URL
            driver.get("https://xueqiu.com/");
            // 等待加载完成
            // 使用 WebDriverWait 等待页面加载完成
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6)); // 等待 30 秒
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));


            String currentUrl = driver.getCurrentUrl();

            Set<Cookie> cookies = driver.manage().getCookies();

            System.out.println(currentUrl);


        } finally {
            // 关闭浏览器
            driver.quit();
        }
    }
}
