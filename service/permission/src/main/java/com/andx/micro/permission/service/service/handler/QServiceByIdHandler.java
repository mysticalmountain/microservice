package com.andx.micro.permission.service.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.ServiceRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-6-21.
 */
@Component
public class QServiceByIdHandler extends GenericServiceHandler<Long, ServiceDto>{

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceDto doHandle(Long aLong, ServiceContext context) throws HandlerException {
        Service service = serviceRepository.findOne(aLong);
        ServiceDto serviceDto = new ServiceDto();
        BeanUtils.copyProperties(service, serviceDto);
        return serviceDto;
    }

    @Override
    public Boolean support(Long aLong, ServiceContext context) {
        return true;
    }
}
