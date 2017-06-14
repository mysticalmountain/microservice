package com.andx.micro.api.core.exception;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class UnifiedException extends Exception {

    private ExceptionType type = ExceptionType.UNKNOWN;

    /**
     * 异常上下文，可以设置业务参数
     */
    private Map<String, Object> context;

    private String message = "";


    public UnifiedException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.message = message;
        this.context = context;
    }

    public UnifiedException(ExceptionType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.message = message;
    }

    public UnifiedException(ExceptionType type, String message) {
        super(message);
        this.type = type;
        this.message = message;
    }

    public UnifiedException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
    public UnifiedException(String message) {
        super(message);
        this.message = message;
    }

    public UnifiedException (Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
