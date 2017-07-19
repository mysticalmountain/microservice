package com.andx.micro.management.login;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.OrgDto;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
public class GetUserOrgHandler extends GenericServiceHandler<Long, Long> {

    @Autowired
    private UserClient userClient;

    @Override
    public Long doHandle(Long aLong, ServiceContext context) throws HandlerException {
        HttpServletRequest httpServletRequest = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Request request = context.getAttribute(Constant.KEY_PRAMS, Request.class);
        Response<OrgDto> response = userClient.queryUserOrg(userId, Constant.MODULE, request.getRequestId());
        return response.getData().getId();
    }

    @Override
    public Boolean support(Long aLong, ServiceContext context) {
        return true;
    }
}
