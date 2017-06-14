package com.andx.micro.permission.dto.permission;

import java.io.Serializable;

/**
 * Created by andongxu on 17-1-17.
 */
public class ValidatorPermissionDto implements Serializable {

    public enum UserType {
        CHANNEL, ROLE
    }

    private String ownerId;

    private UserType userType;

    private String resourceId;

    private String serviceId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
