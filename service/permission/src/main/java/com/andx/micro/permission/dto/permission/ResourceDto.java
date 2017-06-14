package com.andx.micro.permission.dto.permission;

import com.andx.micro.permission.dto.service.ServiceDto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-21.
 */
public class ResourceDto implements Serializable {

    private Long id;

    private ServiceDto serviceDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceDto getServiceDto() {
        return serviceDto;
    }

    public void setServiceDto(ServiceDto serviceDto) {
        this.serviceDto = serviceDto;
    }
}
