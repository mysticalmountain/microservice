package com.andx.micro.management.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/service/\\w+", code = "editService", method = MethodType.PUT, name = "编辑服务", module = "management")
public class EditService extends PutComplexService<Request<ServiceDto>, Response<ServiceDto>>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<ServiceDto> doService(Request<ServiceDto> request, String path, ServiceContext context) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        request.setChannelId(Constant.MODULE);
        return permissionClient.editService(Long.valueOf(id), request);
    }

    private Response<ServiceDto> noCheck(Request<ServiceDto> request, String path, ServiceContext context) {
        return null;
    }

    private Response<ServiceDto> waitCheck(Request<ServiceDto> request, String path, ServiceContext context) {
        return null;
    }

}
