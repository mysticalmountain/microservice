package com.andx.micro.support.web.service.login;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.PostComplexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by andongxu on 17-4-13.
 */
@Component
@Service(path = "/login", code = "login", method = MethodType.POST, name = "登录", module = "用户")
public class LoginService extends PostComplexService<Request<LoginDto>, Response> {

    @Autowired(required = false)
    @Qualifier("usernamePasswordHandler")
    private ServiceHandler<LoginDto, String> usernamePasswordHandler;
    @Autowired(required = false)
    @Qualifier("userProfileHandler")
    private ServiceHandler<Long, List<Long>> userProfileHandler;

    public LoginService() {
    }

    @Override
    protected Response doService(Request<LoginDto> loginDtoRequest, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null && loginDtoRequest.getData().getUsername().equals(session.getAttribute("userId"))) { //判断是否已登录
            return new Response(true);
        }
        LoginDto loginDto = (LoginDto) context.getServiceInputObj(Request.class).getData();
        try {
            String userId = usernamePasswordHandler.handle(loginDto, context);
            if (userProfileHandler !=  null) {
                List<Long> roleIds = userProfileHandler.handle(Long.valueOf(userId), null);
                session.setAttribute("roleIds", roleIds);
            }
            if (userId != null) {
                session.setAttribute("userId", userId);
                return new Response(true);
            } else {
                return new Response(false);
            }
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public LoginService(ServiceHandler... handlers) {

        Collections.addAll(serviceHandlers, handlers);
    }

}
