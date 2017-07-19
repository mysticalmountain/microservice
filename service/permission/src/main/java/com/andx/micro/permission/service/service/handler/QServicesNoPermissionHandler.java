package com.andx.micro.permission.service.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 17-6-21.
 */
@Component
public class QServicesNoPermissionHandler extends GenericServiceHandler<Null, List<ServiceDto>> {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<ServiceDto> doHandle(Null aNull, ServiceContext context) throws HandlerException {
        List<Service> services = serviceRepository.findAll();
        List<Permission> permissions = permissionRepository.findAll();

        List<ServiceDto> serviceDtos = new ArrayList<>();
        for (Service service : services) {
            boolean exists = false;
            for (Permission permission : permissions) {
                Long resourceId = permission.getResource().getId();
                if (permission.getResource().getId() == service.getResource().getId()) {
                    exists = true;
                }
            }
            if (exists == false) {
                ServiceDto serviceDto = new ServiceDto();
                Resource resource = service.getResource();
                ResourceDto resourceDto = new ResourceDto();
                BeanUtils.copyProperties(resource, resourceDto);
                BeanUtils.copyProperties(service, serviceDto);
                serviceDto.setResourceDto(resourceDto);
                serviceDtos.add(serviceDto);
            }
        }
        return serviceDtos;
    }

    @Override
    public Boolean support(Null aNull, ServiceContext context) {
        return true;
    }
}
