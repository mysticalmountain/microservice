package com.andx.micro.management.dto;


import com.andx.micro.api.core.MethodType;

/**
 * Created by andongxu on 17-2-23.
 */
public class PermissionServiceDto {
    private Long id;

    private String code;

    private String content;

    private String system;

    private String module;

    private Operate operate;

    private String resourceId;

    private String permissionId;

    private String path;

    private MethodType method;

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

    public void setModule(String module) {
        this.module = module;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPath() {
        return path;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
