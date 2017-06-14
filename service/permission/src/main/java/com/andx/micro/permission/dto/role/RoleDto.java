package com.andx.micro.permission.dto.role;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by andongxu on 16-11-21.
 */
public class RoleDto implements Serializable {

    private Long id;

    private String name;

    private Set<String> permissionIds;

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

    public void setPermissionIds(Set<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
