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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-5-4.
 */
@Component
@Service(path = "/permissions", code = "queryPermission", name = "查询权限", module = "permission")
public class QueryPermissions extends GetSampleService<Response<List<PermissionDto>>> {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    protected Response<List<PermissionDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String code = prams.get("code") != null ? prams.get("code") : null;
        String name = prams.get("name") != null ? prams.get("name") : null;
        com.andx.micro.permission.model.Service qs = new com.andx.micro.permission.model.Service();
        qs.setCode(code);
        qs.setContent(name);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("code", matcher -> matcher.contains()).withMatcher("content", matcher -> matcher.contains());
        Example<com.andx.micro.permission.model.Service> example = Example.of(qs, exampleMatcher);
        List<com.andx.micro.permission.model.Service> services = serviceRepository.findAll(example);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        for (com.andx.micro.permission.model.Service service : services) {
            Resource resource = service.getResource();
            Permission permission = permissionRepository.findByResource(resource);
            if (permission != null) {
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                ResourceDto resourceDto = new ResourceDto();
                permissionDto.setResourceDto(resourceDto);
                BeanUtils.copyProperties(resource, resourceDto);
                ServiceDto serviceDto = new ServiceDto();
                BeanUtils.copyProperties(service, serviceDto);
                resourceDto.setServiceDto(serviceDto);
                permissionDtos.add(permissionDto);
            }
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(permissionDtos);
        return response;
    }
}
