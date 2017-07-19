package com.andx.micro.permission.dto.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.permission.dto.permission.ResourceDto;

import java.io.Serializable;

/**
 * Created by andongxu on 17-2-23.
 */
public class ServiceDto implements Serializable {

    private Long id;

    private String code;

    private String path;

    private String content;

    private String module;

    private String system;

    private MethodType method;

    private Integer isAudit;

    private ResourceDto resourceDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getModule() {
        return module;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    public int getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
