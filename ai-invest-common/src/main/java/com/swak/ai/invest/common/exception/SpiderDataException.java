package com.swak.ai.invest.common.exception;

/**
 * @author: ljq
 * @date: 2024/12/14
 */
public class SpiderDataException extends RuntimeException {

    public SpiderDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpiderDataException(Throwable cause) {
        super(cause);
    }

    public SpiderDataException(String message) {
        super(message);
    }
}
