package com.andx.micro.api.core.module.flowRate;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class FlowRateException extends UnifiedException {

    public FlowRateException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public FlowRateException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public FlowRateException(ExceptionType type, String message) {
        super(type, message);
    }

    public FlowRateException(String message) {
        super(message);
    }
}
