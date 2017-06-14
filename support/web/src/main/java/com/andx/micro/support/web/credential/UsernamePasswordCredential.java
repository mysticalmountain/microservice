package com.andx.micro.support.web.credential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * User: andongxu
 * Time: 16-1-11 上午10:49
 */
public class UsernamePasswordCredential implements Credential, Serializable {

    public static final String AUTHENTICATION_ATTRIBUTE_PASSWORD = "credential";

    private static final long serialVersionUID = -700605081472810939L;

    /** Password suffix appended to username in string representation. */
    private static final String PASSWORD_SUFFIX = "+password";

    private static final String ID = "USERNAME_PASSWORD";

    @NotNull
    @Size(min=1, message = "required.username")
    private String username;

    @NotNull
    @Size(min=1, message = "required.password")
    private String password;

//    @NotNull
//    @Size(min=1, message = "required.captcha")
//    private String captcha;

    public UsernamePasswordCredential() {}

    public UsernamePasswordCredential(final String userName, final String password) {
        this.username = userName;
        this.password = password;
//        this.captcha = captcha;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(final String userName) {
        this.username = userName;
    }

//    public String getCaptcha() {
//        return captcha;
//    }

//    public void setCaptcha(String captcha) {
//        this.captcha = captcha;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.ID;
    }

    @Override
    public String toString() {
        return this.username + PASSWORD_SUFFIX;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final UsernamePasswordCredential that = (UsernamePasswordCredential) o;

        if (password != null ? !password.equals(that.password) : that.password != null) {
            return false;
        }

        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }

        return true;
    }

//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder()
//                .append(username)
//                .append(password)
//                .toHashCode();
//    }
}