package com.swak.ai.invest.test.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author: ljq
 * @date: 2024/11/19
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootTest
public class DataTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testApi() {


        String result = testRestTemplate.postForObject("http://api.tushare.pro", null, String.class);
        log.info(result);

    }
}

