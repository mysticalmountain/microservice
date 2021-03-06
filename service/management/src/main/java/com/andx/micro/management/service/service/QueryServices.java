package com.andx.micro.management.service.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.PageResponse;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/services", code = "queryServices", name = "查询服务列表", module = "management")
public class QueryServices extends GetComplexService<PageResponse<List<ServiceDto>>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected PageResponse<List<ServiceDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        PageResponse<List<ServiceDto>> response = permissionClient.queryServices(prams, Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
        return response;
    }
}
