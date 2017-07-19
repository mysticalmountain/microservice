package com.andx.micro.core.validator;

/**
 * Created by andongxu on 17-5-1.
 */
public class PermissionValidatorDto {

    private String ownerId;

    private String serviceCode;

    private String requestId;

    private String executeId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExecuteId() {
        return executeId;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }

    @Override
    public String toString() {
        return "PermissionValidatorDto{" +
                "ownerId='" + ownerId + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", requestId='" + requestId + '\'' +
                ", executeId='" + executeId + '\'' +
                '}';
    }
}
