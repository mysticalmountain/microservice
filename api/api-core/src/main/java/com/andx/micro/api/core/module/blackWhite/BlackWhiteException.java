package com.andx.micro.api.core.module.blackWhite;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class BlackWhiteException extends UnifiedException {

    public BlackWhiteException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public BlackWhiteException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public BlackWhiteException(ExceptionType type, String message) {
        super(type, message);
    }

    public BlackWhiteException(String message) {
        super(message);
    }
}
