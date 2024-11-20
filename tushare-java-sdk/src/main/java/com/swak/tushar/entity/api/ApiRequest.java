package com.swak.tushar.entity.api;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * ai create
 * 表示对API服务的请求。
 */
@Data
public class ApiRequest {

    /**
     * API接口的名称，例如 "stock_basic"。
     */
    @JSONField(name = "api_name")
    private String apiName;

    /**
     * 用户的唯一标识符，通过登录pro网站获取。
     */
    private String token;

    /**
     * API调用的参数，例如对于daily接口，可能包括 "start_date" 和 "end_date"。
     */
    private Object params;

    /**
     * 要从API中获取的字段列表，字段之间用逗号分隔，例如 "open,high,low,close"。
     */
    private String fields;

}