package com.andx.micro.management.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/permission/\\w+", code = "deletePermission", method = MethodType.DELETE, name = "删除权限", module = "management")
public class DeletePermission extends DeleteComplexService<Response> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        return permissionClient.deletePermission(Long.valueOf(id), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
