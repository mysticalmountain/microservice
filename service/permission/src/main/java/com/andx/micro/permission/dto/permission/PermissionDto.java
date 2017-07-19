package com.andx.micro.permission.dto.permission;

import com.andx.micro.permission.model.Operate;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-21.
 */
public class PermissionDto implements Serializable {

    private Long id;

    private ResourceDto resourceDto;

    private Operate operate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }
}
