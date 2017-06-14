package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-5-4.
 */
@Component
@Service(path = "/permissions", code = "queryPermission", name = "查询权限", module = "权限")
public class QueryPermissions extends GetSampleService<Response<Set<PermissionDto>>> {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    protected Response<Set<PermissionDto>> doService(Map<String, String[]> prams, String path) throws ServiceException {
        List<Permission> permissions = permissionRepository.findAll();
        Iterator<Permission> permissionIterator = permissions.iterator();
        Set<PermissionDto> permissionDtos = new HashSet<>();
        while (permissionIterator.hasNext()) {
            Permission permission = permissionIterator.next();
            PermissionDto permissionDto = new PermissionDto();
            BeanUtils.copyProperties(permission, permissionDto);
            Resource resource = permission.getResource();
            ResourceDto resourceDto = new ResourceDto();
            permissionDto.setResourceDto(resourceDto);
            BeanUtils.copyProperties(resource, resourceDto);
            if (ResourceType.SERVICE == resource.getResourceType()) {
                com.andx.micro.permission.model.Service service = serviceRepository.findByResource(resource);
                ServiceDto serviceDto = new ServiceDto();
                BeanUtils.copyProperties(service, serviceDto);
                resourceDto.setServiceDto(serviceDto);
            }
            permissionDtos.add(permissionDto);
        }
        Response response = Constant.RESPONSE_SUCCESS.clone();
        response.setData(permissionDtos);
        return response;
    }
}
