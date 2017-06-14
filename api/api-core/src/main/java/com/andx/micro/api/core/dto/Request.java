package com.andx.micro.api.core.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 16-12-12.
 */
public class Request<T> implements Serializable {
//    @NotNull(message = "不允许为空")
    private String serviceId;
    @NotNull(message = "不允许为空")
    private String requestId;

    private String uri;

    @Valid
    private T data;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setData(T data) {
        this.data = data;
    }
}
