package com.swak.ai.invest.web.config;

import com.swak.ai.invest.web.interceptor.UserApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用户api拦截器
 *
 * @author: ljq
 * @date: 2024/12/9
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserApiInterceptor userApiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userApiInterceptor)
                .addPathPatterns("/api/**");
    }
}
