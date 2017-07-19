package com.andx.micro.management.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.OrgDto;
import com.andx.micro.management.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/org/\\w+/roles", code = "queryOrgRoles", name = "查询用户", module = "management")
public class QueryOrgRoles extends GetComplexService<Response<List<RoleDto>>>{

    @Autowired
    private PermissionClient permissionClient;
    @Autowired
    private UserClient userClient;

    @Override
    protected Response<List<RoleDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String reqOrgId = tmp.substring(tmp.lastIndexOf("/") + 1);
        HttpServletRequest httpServletRequest = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) httpServletRequest.getSession().getAttribute("orgId");
        Response<List<OrgDto>> res = userClient.queryListOrgs(orgId, Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
        if (res.getSuccess() == false) {
            throw new ServiceException(res.getErrorMessage());
        }
        List<OrgDto> orgDtos = res.getData();
        boolean exist = false;
        for (OrgDto orgDto : orgDtos) {
            if (orgDto.getId().intValue() == Long.valueOf(reqOrgId).intValue()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new ServiceException(String.format("无权限操作机构[%s]", reqOrgId));
        }
        return permissionClient.queryOrgRoles(Long.valueOf(reqOrgId), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
