package com.andx.micro.api.core.module.service.handler;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class HandlerException extends UnifiedException {
    public HandlerException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public HandlerException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerException(ExceptionType type, String message) {
        super(type, message);
    }

    public HandlerException(String message) {
        super(message);
    }


    public HandlerException(Throwable cause) {
        super(cause);
    }
}

