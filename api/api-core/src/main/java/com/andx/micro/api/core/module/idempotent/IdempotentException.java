package com.andx.micro.api.core.module.idempotent;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class IdempotentException extends UnifiedException {

    public IdempotentException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public IdempotentException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public IdempotentException(ExceptionType type, String message) {
        super(type, message);
    }

    public IdempotentException(String message) {
        super(message);
    }
}
