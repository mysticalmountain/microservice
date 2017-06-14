package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@com.andx.micro.api.core.Service(path = "/services/noPermission", code = "queryServicesNoPermission", name = "查询未设置权限的服务", module = "权限")
public class QueryServicesNoPermission extends GetSampleService<Response<Set<ServiceDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response<Set<ServiceDto>> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
        List<Service> services = serviceRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        List<Permission> permissions = permissionRepository.findAll();


        Set<ServiceDto> serviceDtos = new HashSet<>();
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
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(serviceDtos);
        return response;
    }
}
