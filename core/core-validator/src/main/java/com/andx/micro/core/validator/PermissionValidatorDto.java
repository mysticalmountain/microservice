package com.andx.micro.core.validator;

/**
 * Created by andongxu on 17-5-1.
 */
public class PermissionValidatorDto {

    private String ownerId;

    private String serviceCode;

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
}
