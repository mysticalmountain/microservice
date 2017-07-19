package com.andx.micro.user.service.user.session;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.service.user.QueryOrgUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
@Service(path = "/orgs/session/users", code = "queryOrgsSessionUsers", name = "查询登录用户所属机构的用户", module = "user")
public class QueryOrgsSessionUsers extends GetComplexService<Response<List<UserDto>>> {

    @Autowired
    private QueryOrgUsers queryOrgsUsers;

    @Override
    protected Response<List<UserDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        if (orgId == null || orgId.equals("")) {
            throw new ServiceException("获取机构失败");
        }
        return queryOrgsUsers.doService(null, "/orgs/" + String.valueOf(orgId) + "/users");
    }
}
