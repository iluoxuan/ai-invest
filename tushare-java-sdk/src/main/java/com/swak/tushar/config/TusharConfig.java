package com.swak.tushar.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@RequiredArgsConstructor
@Configuration
public class TusharConfig {

    private final TusharProperties tusharProperties;

    @Bean
    public CloseableHttpClient tusharHttpClient() {

        TusharProperties.HttpClientConfig config = tusharProperties.getHttpClientConfig();
        // 创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(config.getMaxTotal());       // 设置最大连接数
        connectionManager.setDefaultMaxPerRoute(config.getDefaultMaxPerRoute()); // 设置每个路由的最大连接数

        // 创建 HttpClient
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(config.getConnectTimeout())  // 连接超时时间
                        .setSocketTimeout(config.getSocketTimeout())   // 读取超时时间
                        .setConnectionRequestTimeout(config.getConnectionRequestTimeout()) // 连接请求超时时间
                        .build())
                .build();
    }
}

