package com.andx.micro.support.web.authentication;

import com.andx.micro.support.web.credential.Credential;

/**
 * Created by andongxu on 17-3-30.
 */
public interface AuthenticationHandler {

    HandlerResult authenticate(Credential credential);

    boolean support(Credential credential);

}
