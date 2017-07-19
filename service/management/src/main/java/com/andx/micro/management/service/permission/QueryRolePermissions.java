package com.andx.micro.management.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-12.
 */
@Component
@Service(path = "/role/\\w+/permissions", code = "queryRolePermissions", method = MethodType.GET, name = "查询角色的权限", module = "management")
public class QueryRolePermissions extends GetComplexService<Response<List<PermissionDto>>> {
    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<List<PermissionDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String id = tmp.substring(tmp.lastIndexOf("/") + 1);
        return permissionClient.queryRolePermissions(Long.valueOf(id), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
