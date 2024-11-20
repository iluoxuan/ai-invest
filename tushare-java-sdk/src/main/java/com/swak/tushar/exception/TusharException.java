package com.swak.tushar.exception;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
public class TusharException extends RuntimeException {

    private int code;

    private String msg;

    public TusharException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
