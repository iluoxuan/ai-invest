package com.swak.ai.invest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Configuration
public class HttpConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
