package com.andx.micro.management.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.NewPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/permission", code = "newPermission", method = MethodType.POST, name = "新建权限", module = "management")
public class NewPermission extends PostComplexService<Request<List<NewPermissionDto>>, Response>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Request<List<NewPermissionDto>> listRequest, ServiceContext context) throws ServiceException {
        listRequest.setChannelId(Constant.MODULE);
        return permissionClient.newPermission(listRequest);
    }
}
