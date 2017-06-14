package com.andx.micro.user.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 17-2-21.
 */
public class RolebakDto implements Serializable {

    private Long roleId;

    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
