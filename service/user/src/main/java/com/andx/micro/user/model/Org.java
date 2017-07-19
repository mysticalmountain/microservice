package com.andx.micro.user.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andongxu on 17-6-30.
 */
@Entity
public class Org extends BaseEntity {

    private String name;

    private Integer od;

    @OneToOne(targetEntity = Org.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Org parent;

    @OneToMany(targetEntity = Org.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Org> sons;

    @OneToMany(mappedBy = "org", targetEntity = User.class, fetch = FetchType.EAGER)
    private List<User> users;

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

    public Org getParent() {
        return parent;
    }

    public void setParent(Org parent) {
        this.parent = parent;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Org> getSons() {
        return sons;
    }

    public void setSons(List<Org> sons) {
        this.sons = sons;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
