package com.swak.tushar.entity.api;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@Data
public class ApiResponse {

    public static final int httpError = 999;
    public static final int ok = 0;

    /**
     * 0 正常
     */
    private int code;

    private String msg;

    private String data;

    public boolean isSuccess() {
        return code == ok;
    }


}
