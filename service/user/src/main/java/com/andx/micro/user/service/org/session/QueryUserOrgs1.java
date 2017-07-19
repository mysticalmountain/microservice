package com.andx.micro.user.service.org.session;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.service.org.QueryListOrgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
@Service(path = "/user-orgs", code = "queryUserOrgs", name = "查询登录用户子机构列表", module = "user")
public class QueryUserOrgs1 extends GetComplexService<Response<List<OrgDto>>> {

    @Autowired
    private QueryListOrgs queryListOrgs;

    @Override
    protected Response<List<OrgDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        if (orgId == null || orgId.equals("")) {
            throw new ServiceException("获取机构失败");
        }
        return queryListOrgs.doService(null, "/service/" + orgId);
    }
}
