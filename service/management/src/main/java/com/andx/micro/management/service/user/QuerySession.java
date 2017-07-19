package com.andx.micro.management.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.management.dto.SessionDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-11.
 */
@Component
@Service(path = "/user/session", code = "queryUserSession", name = "查询用户session数据", module = "management")
public class QuerySession extends GetComplexService<Response<SessionDto>> {
    @Override
    protected Response<SessionDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        Long userId = (Long) request.getSession().getAttribute("userId");
        List<Long> roleIds = (List<Long>) request.getSession().getAttribute("roleIds");
        SessionDto sessionDto = new SessionDto();
        sessionDto.setOrgId(orgId);
        sessionDto.setUserId(userId);
        sessionDto.setRoleIds(roleIds);
        Response<SessionDto> response = new Response<>(Constant.MSG_SUCCESS, true);
        response.setData(sessionDto);
        return response;
    }
}
