package com.andx.micro.api.core.dto;

/**
 * Created by andongxu on 17-1-16.
 */
public class BaseResponse<T> extends Response<T> {


    private String serviceId;
    private String requestId;
    private T data;
    private Boolean success;
    private String errorCode;
    private String errorMessage;

    public BaseResponse() {
    }

    public BaseResponse(String errorCode, String errorMessage, Boolean success) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
