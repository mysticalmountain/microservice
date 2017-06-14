package com.andx.micro.support.web.authentication;

/**
 * Created by andongxu on 17-3-30.
 */
public class DefaultHandlerResult implements HandlerResult {

    private String code;
    private String message;
    private Boolean success;
    private Principal principal;
    private String handlerName;

    public DefaultHandlerResult() {}

    public DefaultHandlerResult(Boolean success) {
        this.success = success;
    }

    public DefaultHandlerResult(String code, String message, Boolean success, String handlerName) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.handlerName = handlerName;
    }

    public DefaultHandlerResult(String code, String message, Boolean success, Principal principal, String handlerName) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.principal = principal;
        this.handlerName = handlerName;
    }

    @Override
    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public Boolean isSuccess() {
        return success;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
