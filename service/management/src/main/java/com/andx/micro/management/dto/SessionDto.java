package com.andx.micro.management.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andongxu on 17-7-11.
 */
public class SessionDto implements Serializable {

    private Long userId;

    private Long orgId;

    private List<Long> roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
