package com.swak.ai.invest;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.swak.ai.invest.data.DataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author: ljq
 * @date: 2024/10/24
 */
@Import(DataConfig.class)
@EnableMethodCache(basePackages = "com.swak.ai.invest", proxyTargetClass = true)
@SpringBootApplication
public class AiInvestApplication {

    public static void main(String[] args) {

        SpringApplication.run(AiInvestApplication.class, args);
    }
}
