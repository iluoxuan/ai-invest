package com.swak.ai.invest.data.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spider")
public class SpiderProperties {


    private String chromeDriverPath = "D:\\ljq\\work\\config\\chromedriver-win64\\chromedriver.exe";


}
