package com.andx.micro.support.web.service.login;

import java.io.Serializable;

/**
 * Created by andongxu on 17-2-27.
 */
public class LoginDto implements Serializable {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
