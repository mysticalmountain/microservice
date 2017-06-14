package com.andx.micro.permission.dto.permission;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-21.
 */
public class PermissionDto implements Serializable {

    private Long id;

    private ResourceDto resourceDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }
}
