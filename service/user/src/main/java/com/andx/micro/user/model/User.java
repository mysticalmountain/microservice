package com.andx.micro.user.model;

import com.andx.micro.user.model.enums.UserType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by andongxu on 16-6-6.
 */

@Entity
@Table
public class User extends BaseEntity {

    @Column(length = 64)
    private String name;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

//    @OneToMany(mappedBy = "user", targetEntity = Authority.class, cascade = CascadeType.PERSIST)
    @OneToMany(mappedBy = "user", targetEntity = Authority.class, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToOne(mappedBy = "user", targetEntity = Profile.class)
//    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToMany(targetEntity = Rolebak.class, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Rolebak",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Rolebak> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Rolebak> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rolebak> roles) {
        this.roles = roles;
    }
}
