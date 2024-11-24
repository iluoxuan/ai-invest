package com.swak.ai.invest.data;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.swak.ai.invest.data.config.SpiderProperties;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@EnableMethodCache(basePackages = "com.swak.ai.invest.data")
@ComponentScan
@RequiredArgsConstructor
@EnableConfigurationProperties(SpiderProperties.class)
@Configuration
public class DataConfig {

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";

    private final SpiderProperties spiderProperties;

    @Bean
    public WebDriver webDriver() {

        System.setProperty("webdriver.chrome.driver", spiderProperties.getChromeDriverPath());
        // 创建 ChromeOptions 对象
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("no-sandbox");//很关键
        // 创建 ChromeOptions 对象
        options.addArguments("user-agent=" + USER_AGENT);

        // 设置开发者模式
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

}
