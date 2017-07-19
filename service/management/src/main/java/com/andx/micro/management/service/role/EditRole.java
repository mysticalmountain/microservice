package com.andx.micro.management.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/role/\\w+", code = "eidtRole", method = MethodType.PUT, name = "编辑角色", module = "management")
public class EditRole extends PutComplexService<Request<RoleDto>, Response<RoleDto>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<RoleDto> doService(Request<RoleDto> requestRoleDto, String path, ServiceContext context) throws ServiceException {
        String roleId = path.substring(path.lastIndexOf("/") + 1);
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        Response<List<RoleDto>> res = permissionClient.queryOrgRoles(orgId, Constant.MODULE, requestRoleDto.getRequestId());
        if (res.getSuccess() == false) {
            throw new ServiceException(res.getErrorMessage());
        }
        List<RoleDto> roleDtos = res.getData();
        boolean exist = false;
        for (RoleDto roleDto : roleDtos) {
            if (roleDto.getId().intValue() == Long.valueOf(roleId).intValue()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new ServiceException(String.format("无权限操作角色[%s]", roleId));
        }
        requestRoleDto.setChannelId(Constant.MODULE);
        return permissionClient.editRole(Long.valueOf(roleId), requestRoleDto);
    }
}
