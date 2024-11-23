package com.swak.ai.invest;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ljq
 * @date: 2024/10/24
 */
@EnableMethodCache(basePackages = "com.swak.ai.invest.service")
@SpringBootApplication
public class AiInvestApplication {

    public static void main(String[] args) {

        SpringApplication.run(AiInvestApplication.class, args);
    }
}
