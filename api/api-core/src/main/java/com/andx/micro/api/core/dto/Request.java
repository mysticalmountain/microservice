package com.andx.micro.api.core.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 16-12-12.
 */
public class Request<T> implements Serializable {

    @NotNull(message = "不允许为空")
    private String requestId;

    @NotNull(message = "不允许为空")
    private String channelId;

    @Valid
    private T data;

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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
