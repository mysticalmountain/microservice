package com.andx.micro.api.core.module.validator;

import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.exception.UnifiedException;

import java.util.Map;

/**
 * Created by andongxu on 16-12-30.
 */
public class ValidatorException extends UnifiedException {


    public ValidatorException(ExceptionType type, String message, Map<String, Object> context, Throwable cause) {
        super(type, message, context, cause);
    }

    public ValidatorException(ExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(ExceptionType type, String message) {
        super(type, message);
    }

    public ValidatorException(String message) {
        super(message);
    }
}
