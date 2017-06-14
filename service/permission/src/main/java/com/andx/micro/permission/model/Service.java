package com.andx.micro.permission.model;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.support.jpa.model.CheckEntity;
import com.andx.micro.support.jpa.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

/**
 * Created by andongxu on 16-8-29.
 */
@javax.persistence.Entity
public class Service extends BaseEntity {

    @Column(length = 32)
    private String code;

    @Column(length = 255)
    private String content;

    @Column(length = 8)
    private String system;

    @Column(length = 8)
    private String module;

    @Column(length = 255)
    private String path;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private MethodType method;

    /**
     * 0：不需审核
     * 1：需审核
     */
    @Column(length = 1)
    private Integer isAudit;


    @OneToOne
    @JoinColumn(name = "resource_id", unique = true, nullable = false, updatable = false)
    private Resource resource;

    @PrePersist
    public void prePersist() {
        super.prePersist();
        this.isAudit = 0;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Resource getResource() {
        return resource;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    @Override
    public String toString() {
        return "Service{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", system='" + system + '\'' +
                ", module='" + module + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", isAudit=" + isAudit +
                ", resource=" + resource +
                '}';
    }
}
