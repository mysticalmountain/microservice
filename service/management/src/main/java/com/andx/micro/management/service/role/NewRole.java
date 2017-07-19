package com.andx.micro.management.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.OrgDto;
import com.andx.micro.management.dto.RoleDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/role", code = "newRole", method = MethodType.POST, name = "新建角色", module = "management")
public class NewRole extends PostComplexService<Request<RoleDto>, Response<RoleDto>> {

    @Autowired
    private PermissionClient permissionClient;
    @Autowired
    private UserClient userClient;

    @Override
    protected Response<RoleDto> doService(Request<RoleDto> request, ServiceContext context) throws ServiceException {
        RoleDto roleDto = request.getData();
        HttpServletRequest httpServletRequest = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) httpServletRequest.getSession().getAttribute("orgId");
        Response<List<OrgDto>> res = userClient.queryListOrgs(orgId, Constant.MODULE, request.getRequestId());
        if (res.getSuccess() == false) {
            throw new ServiceException(res.getErrorMessage());
        }
        List<OrgDto> orgDtos = res.getData();
        boolean exist = false;
        for (OrgDto orgDto : orgDtos) {
            if (orgDto.getId().intValue() == roleDto.getOrgId().intValue()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new ServiceException(String.format("无权限操作机构[%s]", roleDto.getOrgId()));
        }
        request.setChannelId(Constant.MODULE);
        return permissionClient.newRole(request);
    }
}
