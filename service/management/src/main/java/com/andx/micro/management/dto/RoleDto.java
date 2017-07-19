package com.andx.micro.management.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by andongxu on 16-11-21.
 */
public class RoleDto implements Serializable {

    private Long id;

    private String name;

    private Set<String> permissionIds;

    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPermissionIds() {
        return permissionIds;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setPermissionIds(Set<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
