package com.andx.micro.support.jpa.model;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by andongxu on 16-6-7.
 */
@MappedSuperclass
public class CheckEntity extends Entity {

    @Column(length = 8, name = "check_type", nullable = false )
    @Enumerated(EnumType.STRING)
    private CheckType checkType;

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateTime = new Timestamp(new java.util.Date().getTime());
    }

    @PrePersist
    public void prePersist() {
        super.prePersist();
        System.out.println();
        if (checkType == null) {
            checkType = CheckType.NO;
        }
    }
}
