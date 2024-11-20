package com.swak.tushar.execute;

import com.alibaba.fastjson2.JSON;
import com.swak.tushar.config.TusharProperties;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.api.ApiRequest;
import com.swak.tushar.entity.api.ApiResponse;
import com.swak.tushar.entity.api.ApiResponseData;
import com.swak.tushar.exception.TusharException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * api调用
 *
 * @author: ljq
 * @date: 2024/11/20
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultApiExecute {

    private final CloseableHttpClient tusharHttpClient;

    private final TusharProperties tusharProperties;

    public <T> List<T> execute(ApiNameEnum apiName, Object params, Class<T> clazz) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setApiName(apiName.name());
        apiRequest.setToken(tusharProperties.getToken());
        apiRequest.setParams(params);
        try {

            HttpPost request = new HttpPost(tusharProperties.getUrl());
            request.setHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(JSON.toJSONString(apiRequest));
            request.setEntity(entity);

            try (CloseableHttpResponse response = tusharHttpClient.execute(request)) {

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    String result = EntityUtils.toString(response.getEntity());
                    ApiResponse apiResponse = JSON.parseObject(result, ApiResponse.class);
                    Assert.notNull(apiResponse, "apiResponse is null");
                    if (!apiResponse.isSuccess()) {
                        throw new TusharException(apiResponse.getCode(), apiResponse.getMsg());
                    }
                    ApiResponseData apiResponseData = JSON.parseObject(apiResponse.getData(), ApiResponseData.class);
                    return apiResponseData.create(clazz);
                } else {
                    throw new TusharException(ApiResponse.httpError, "Unexpected status code: " + statusCode);
                }

            }

        } catch (Exception e) {
            log.error("execute ", e);
            throw new TusharException(ApiResponse.httpError, e.getMessage());
        }

    }
}
