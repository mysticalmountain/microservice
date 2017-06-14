package com.andx.micro.permission.dto;

import com.andx.micro.api.core.dto.BaseRequest;

/**
 * Created by andongxu on 17-1-17.
 */
public class PermissionRequest extends BaseRequest {

    public enum UserType {
        CHANNEL, ROLE
    }

    private String ownerId;

    private UserType userType;

    private String resourceId;

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
}
