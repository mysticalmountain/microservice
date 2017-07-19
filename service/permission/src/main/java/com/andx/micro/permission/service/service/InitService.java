package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.andx.micro.permission.repository.ResourceRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-3.
 */
@Component
@Service(path = "/service/init", code = "initService", method = MethodType.POST, name = "初始化基础数据（服务）", module = "permission")
public class InitService extends PostSampleService<Request<ServiceDto>, Response> {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    protected Response doService(Request<ServiceDto> serviceDtoRequest) throws ServiceException {
        ServiceDto serviceDto = serviceDtoRequest.getData();
        com.andx.micro.permission.model.Service service = serviceRepository.findByCode(serviceDto.getCode());
        if (service == null) {
            service = new com.andx.micro.permission.model.Service();
            Resource resource = new Resource();
            resource.setResourceType(ResourceType.SERVICE);
            resource = resourceRepository.save(resource);
            service.setResource(resource);
        }
        service.setContent(serviceDto.getContent());
        service.setCode(serviceDto.getCode());
        service.setMethod(serviceDto.getMethod());
        service.setPath(serviceDto.getPath());
        service.setSystem(serviceDto.getSystem());
        service.setModule(serviceDto.getModule());
        serviceRepository.save(service);
        return Constant.RESPONSE_SUCCESS.clone();
    }
}
