package com.andx.micro.management.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/services/noPermission", code = "queryServicesNoPermission", method = MethodType.GET, name = "查询未添加权限的服务", module = "management")
public class QueryServiceNoPermission extends GetComplexService<Response<List<ServiceDto>>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<List<ServiceDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        return permissionClient.queryServicesNoPermission(Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
