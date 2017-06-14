package com.andx.micro.support.web.service.login;

import javax.servlet.ServletException;

/**
 * Created by andongxu on 17-4-14.
 */
public class NoLoginException extends ServletException {
    public NoLoginException(String message) {
        super(message);
    }
}
