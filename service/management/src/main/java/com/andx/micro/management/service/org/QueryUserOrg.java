package com.andx.micro.management.service.org;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.OrgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/user/org", code = "queryUserOrg", name = "查询用户机构", module = "management")
public class QueryUserOrg extends GetComplexService<Response<OrgDto>> {

    @Autowired
    private UserClient userClient;

    @Override
    protected Response<OrgDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        return userClient.queryOrg(orgId, Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
