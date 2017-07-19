package com.andx.micro.management.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 17-6-17.
 */
public class MenuDto implements Serializable  {

    private Long id;

    private String name;

    private String action;

    private Long pid;

    private Integer od;

    private Boolean open = true;

    private List<MenuDto> sons = new ArrayList<MenuDto>();

    private ResourceDto resourceDto;

//    private MenuDto parent;

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<MenuDto> getSons() {
        return sons;
    }

    public void setSons(List<MenuDto> sons) {
        this.sons = sons;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setAction(String action) {
        this.action = action;
    }

//    public MenuDto getParent() {
//        return parent;
//    }

//    public void setParent(MenuDto parent) {
//        this.parent = parent;
//    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }
}
