package com.andx.micro.management.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 17-2-3.
 */
public class QueryRoleDto implements Serializable {

    private Long id;

    private String name;

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
}
