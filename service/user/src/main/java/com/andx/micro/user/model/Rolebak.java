package com.andx.micro.user.model;

import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by andongxu on 16-8-25.
 */
@javax.persistence.Entity
public class Rolebak extends BaseEntity {


    private Long roleId;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
