package com.andx.micro.user.model;


import com.andx.micro.user.model.enums.AuthType;

import javax.persistence.*;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class Authority extends BaseEntity {

    @Column(length = 32, unique = true, nullable = false)
    private String accountNo;

    @Column(length = 32, nullable = false)
    private String password;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }
}
