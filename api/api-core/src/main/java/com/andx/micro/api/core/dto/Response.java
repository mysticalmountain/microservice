package com.andx.micro.api.core.dto;


import java.io.Serializable;

/**
 * Created by andongxu on 16-12-12.
 */
public class Response<T> implements Serializable, Cloneable {

    private String serviceId;
    private String requestId;
    private Boolean success;
    private String errorCode;
    private String errorMessage;
    private T data;

    public Response() {}

    public Response(String errorCode, String errorMessage, Boolean success) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public Response(String errorMessage, Boolean success) {
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public Response(Boolean success) {
        this.success = success;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getRequestId() {
        return requestId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }

    @Override
    public Response<T> clone() {
        Response response = null;
        try {
            response = (Response) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "serviceId='" + serviceId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
