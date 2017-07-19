package com.andx.micro.permission.dto.permission;

import com.andx.micro.permission.model.Operate;

import java.io.Serializable;

/**
 * Created by andongxu on 17-5-5.
 */
public class NewPermissionDto implements Serializable {

    private String resourceId;

    private Operate operate;

    public String getResourceId() {
        return resourceId;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
