package com.andx.micro.management.login;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.support.web.service.login.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-4-13.
 */
@Component
public class UsernamePasswordHandler extends GenericServiceHandler<LoginDto, String> {

    @Autowired
    private UserClient userClient;

    @Override
    public String doHandle(LoginDto loginDto, ServiceContext context) throws HandlerException {
        Request request = context.getAttribute(Constant.KEY_PRAMS, Request.class);
        Response<Long> res = userClient.queryUserUsernamePassword(loginDto.getUsername(), loginDto.getPassword(), Constant.MODULE, request.getRequestId());
        if (res.getSuccess() == true) {
            return String.valueOf(res.getData());
        } else {
            throw new HandlerException(res.getErrorMessage());
        }
    }

    @Override
    public Boolean support(LoginDto loginDto, ServiceContext context) {
        return true;
    }
}
