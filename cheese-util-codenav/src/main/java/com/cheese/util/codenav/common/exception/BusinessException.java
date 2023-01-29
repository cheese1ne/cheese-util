package com.cheese.util.codenav.common.exception;

/**
 * 自定义业务异常
 * @author sobann
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
