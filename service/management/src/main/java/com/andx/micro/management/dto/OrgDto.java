package com.andx.micro.management.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 17-6-30.
 */
public class OrgDto implements Serializable {

    private Long id;

    private String name;

    private Integer od;

    private Long pid;

    private List<OrgDto> sons = new ArrayList<>();

    private Boolean open = true;

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

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public Long getPid() {
        return pid;
    }

    public List<OrgDto> getSons() {
        return sons;
    }

    public void setSons(List<OrgDto> sons) {
        this.sons = sons;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
