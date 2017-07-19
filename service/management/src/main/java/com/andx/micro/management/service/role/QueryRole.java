package com.andx.micro.management.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-12.
 */
@Component
@Service(path = "/role/\\w+", code = "queryRole", method = MethodType.GET, name = "查询角色", module = "management")
public class QueryRole extends GetComplexService<Response<RoleDto>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<RoleDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        return permissionClient.queryRole(Long.valueOf(id), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
