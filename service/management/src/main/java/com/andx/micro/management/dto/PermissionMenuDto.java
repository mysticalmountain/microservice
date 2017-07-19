package com.andx.micro.management.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 17-6-30.
 */
public class PermissionMenuDto implements Serializable {

    private Long id;

    private String name;

    private String action;

    private Long pid;

    private Integer od;

    private Operate operate;

    private Long resourceId;

    private Long permissionId;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
