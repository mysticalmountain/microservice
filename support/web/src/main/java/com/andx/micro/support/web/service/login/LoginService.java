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
import com.andx.micro.core.util.Constant;
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
    @Qualifier("getUserRoleHandler")
    private ServiceHandler<Long, List<Long>> getUserRoleHandler;
    @Autowired(required = false)
    @Qualifier("getUserOrgHandler")
    private ServiceHandler<Long, Long> getUserOrgHandler;

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
            if (userId == null) {
                return Constant.RESPONSE_FAIL.clone();
            }
            session.setAttribute("userId", Long.valueOf(userId));
            if (getUserRoleHandler != null) {
                List<Long> roleIds = getUserRoleHandler.handle(Long.valueOf(userId), context);
                session.setAttribute("roleIds", roleIds);
            }
            if (getUserOrgHandler != null) {
                Long orgId = getUserOrgHandler.handle(Long.valueOf(userId), context);
                session.setAttribute("orgId", orgId);
            }
            return Constant.RESPONSE_SUCCESS.clone();
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
