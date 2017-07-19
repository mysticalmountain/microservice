package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-6-17.
 */
@Component
@Service(path = "/service/\\w+", code = "editService", method = MethodType.PUT, name = "编辑服务", module = "permission")
public class EditService extends PutSampleService<Request<ServiceDto>, Response<Boolean>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    protected Response<Boolean> doService(Request<ServiceDto> serviceDtoRequest, String path) throws ServiceException {
        String serviceId = path.substring(path.lastIndexOf("/") + 1);
        com.andx.micro.permission.model.Service service = serviceRepository.findOne(Long.valueOf(serviceId));
        ServiceDto serviceDto = serviceDtoRequest.getData();
        BeanUtils.copyProperties(serviceDto, service);
        service.setId(Long.valueOf(serviceId));
        serviceRepository.save(service);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(true);
        return response;
    }
}
