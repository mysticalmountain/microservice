package com.andx.micro.permission.model;

import com.andx.micro.support.jpa.model.CheckEntity;
import com.andx.micro.support.jpa.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
@javax.persistence.Entity
public class Menu extends BaseEntity {

    private String name;

    private String action;

    @OneToOne(targetEntity = Menu.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Menu parent;

    @OneToMany(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Menu> sons;

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

    public Set<Menu> getSons() {
        return sons;
    }

    public void setSons(Set<Menu> sons) {
        this.sons = sons;
    }
}
