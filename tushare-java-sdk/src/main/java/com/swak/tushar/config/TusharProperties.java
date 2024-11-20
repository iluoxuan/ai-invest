package com.swak.tushar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/11/19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tushar")
public class TusharProperties {

    private String token = "43cc6404f09a781b4c46a8cf5d74278637f69160ac7c396986d8424a";

    private String url = "http://api.tushare.pro";

    private HttpClientConfig httpClientConfig = new HttpClientConfig();

    @Data
    public class HttpClientConfig {

        private int defaultMaxPerRoute = 50;

        private int maxTotal = 500;

        private int connectionRequestTimeout = 6 * 1000;
        private int connectTimeout = 10 * 1000;
        private int socketTimeout = 60 * 1000;

    }


}
