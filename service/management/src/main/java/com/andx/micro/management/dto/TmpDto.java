package com.andx.micro.management.dto;


import java.io.Serializable;
import java.lang.invoke.MethodType;

/**
 * Created by andongxu on 17-2-23.
 */
public class TmpDto implements Serializable {

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
