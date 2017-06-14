package com.andx.micro.api.core.module.service;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class ServiceException extends UnifiedException {
    public ServiceException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public ServiceException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(ExceptionType type, String message) {
        super(type, message);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

