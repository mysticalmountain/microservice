package com.andx.micro.management.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.PermissionMenuDto;
import com.andx.micro.management.dto.PermissionServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-12.
 */
@Component
@Service(path = "/permissions/services", code = "queryPermissionsServices", method = MethodType.GET, name = "查询服务权限", module = "management")
public class QueryPermissionsServices extends GetComplexService<Response<List<PermissionServiceDto>>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<List<PermissionServiceDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        prams.put(Constant.KEY_CHANNEL_ID, Constant.MODULE);
        return permissionClient.queryPermissionsServices(prams);
    }
}
