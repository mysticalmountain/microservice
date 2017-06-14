package com.andx.micro.support.web.dispatch;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class DispatchException extends UnifiedException {

    public DispatchException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public DispatchException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public DispatchException(ExceptionType type, String message) {
        super(type, message);
    }

    public DispatchException(String message) {
        super(message);
    }
}

