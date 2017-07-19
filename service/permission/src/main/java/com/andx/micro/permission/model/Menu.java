package com.andx.micro.permission.model;

import com.andx.micro.support.jpa.model.CheckEntity;
import com.andx.micro.support.jpa.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
@javax.persistence.Entity
public class Menu extends BaseEntity {

    private String name;

    private String action;

    private Integer od;

    @OneToOne(targetEntity = Menu.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Menu parent;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Menu> sons;

    @OneToOne
    @JoinColumn(name = "resource_id", unique = true, nullable = false, updatable = false)
    private Resource resource;

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

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getSons() {
        return sons;
    }

    public void setSons(List<Menu> sons) {
        this.sons = sons;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", action='" + action + '\'' +
//                ", parent=" + parent +
//                ", sons=" + sons +
                '}';
    }

    public Integer getOd() {
        return od;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

}
