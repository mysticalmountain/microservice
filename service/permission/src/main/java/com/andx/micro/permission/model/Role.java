package com.andx.micro.permission.model;

import com.andx.micro.support.jpa.model.CheckEntity;
import com.andx.micro.support.jpa.model.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-8-25.
 */
@javax.persistence.Entity
@Table
public class Role extends BaseEntity {

    private String name;

    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Role parent;

    @OneToMany(targetEntity = Role.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Role> sons;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Role_Permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public Set<Role> getSons() {
        return sons;
    }

    public void setSons(Set<Role> sons) {
        this.sons = sons;
    }
}
