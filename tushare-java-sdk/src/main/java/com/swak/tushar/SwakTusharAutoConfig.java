package com.swak.tushar;

import com.swak.tushar.config.TusharProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/11/19
 */
@ComponentScan
@EnableConfigurationProperties(TusharProperties.class)
@Configuration
public class SwakTusharAutoConfig {


}
