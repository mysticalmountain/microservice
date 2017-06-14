package com.andx.micro.permission.model;

import com.andx.micro.support.jpa.model.CheckEntity;
import com.andx.micro.support.jpa.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

import java.util.Set;

/**
 * Created by andongxu on 16-11-14.
 */
@javax.persistence.Entity
public class Resource extends BaseEntity {

    @Column(length = 8, name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @OneToMany(targetEntity = Permission.class, mappedBy = "resource")
    private Set<Permission> permissions;


    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

