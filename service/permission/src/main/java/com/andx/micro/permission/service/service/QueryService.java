package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.ServiceRepository;
import com.andx.micro.permission.service.service.handler.QServiceByIdHandler;
import com.andx.micro.permission.service.service.handler.QServicesNoPermissionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@com.andx.micro.api.core.Service(path = "/service/\\w+", code = "queryService", name = "按条件查询服务", module = "permission")
public class QueryService extends GetSampleService<Response<ServiceDto>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Response<ServiceDto> doService(Map<String, String> stringMap, String path) throws ServiceException {
        String permissionId = path.substring(path.lastIndexOf("/") + 1, path.length());
        Service service = null;
        String type = stringMap.get("type");
        if ("code".equals(type)) {
            service = serviceRepository.findByCode(permissionId);
        } else {
            service = serviceRepository.findOne(Long.valueOf(permissionId));
        }
        ServiceDto serviceDto = new ServiceDto();
        BeanUtils.copyProperties(service, serviceDto);
        Response<ServiceDto> response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(serviceDto);
        return response;
    }


}
