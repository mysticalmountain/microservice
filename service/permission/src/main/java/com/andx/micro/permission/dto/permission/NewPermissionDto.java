package com.andx.micro.permission.dto.permission;

import java.io.Serializable;

/**
 * Created by andongxu on 17-5-5.
 */
public class NewPermissionDto implements Serializable {

    private String resourceId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
