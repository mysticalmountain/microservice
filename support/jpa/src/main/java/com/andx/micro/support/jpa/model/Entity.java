package com.andx.micro.support.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by andongxu on 16-6-7.
 */
@MappedSuperclass
public class Entity implements Serializable {

    @Column(name = "created_time", nullable = false)
    protected Date createdTime;
    @Column(name = "last_update_time", nullable = false)
    protected Date lastUpdateTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateTime = new Timestamp(new java.util.Date().getTime());
    }

    @PrePersist
    public void prePersist() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        createdTime = now;
        lastUpdateTime = now;
    }
}
