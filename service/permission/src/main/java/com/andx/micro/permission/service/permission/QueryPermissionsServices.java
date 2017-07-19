package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.dto.permission.PermissionServiceDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@com.andx.micro.api.core.Service(path = "/permissions/services", code = "queryPermissionsService", name = "查询带权限的服务", module = "permission")
public class QueryPermissionsServices extends GetSampleService<Response<List<PermissionServiceDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response<List<PermissionServiceDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {
        Service qs = new Service();
        qs.setCode("".equals(stringMap.get("code")) ? null : stringMap.get("code"));
        qs.setContent("".equals(stringMap.get("name")) ? null : stringMap.get("name"));
        qs.setMethod(stringMap.get("method") != null && !stringMap.get("method").equals("") ? MethodType.valueOf(stringMap.get("method")) : null);
        qs.setIsAudit(stringMap.get("isAudit") != null && !stringMap.get("isAudit").equals("") ? Integer.valueOf(stringMap.get("isAudit")) : null);
        qs.setModule("".equals(stringMap.get("module")) ? null : stringMap.get("module"));
        Example<Service> example = Example.of(qs);
        List<Service> services = serviceRepository.findAll(example);
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionServiceDto> serviceDtos = new ArrayList<>();
        for (Service service : services) {
            boolean exists = false;
            for (Permission permission : permissions) {
                Long resourceId = permission.getResource().getId();
                if (permission.getResource().getId().intValue() == service.getResource().getId().intValue()) {
                    exists = true;
                    break;
                }
            }
            if (exists == true) {
                Resource resource = service.getResource();
                Iterator<Permission> permissionIterator = resource.getPermissions().iterator();
                while(permissionIterator.hasNext()) {
                    Permission permission = permissionIterator.next();
                    PermissionServiceDto servicePermissionDto = new PermissionServiceDto();
                    BeanUtils.copyProperties(service, servicePermissionDto);
                    servicePermissionDto.setResourceId(String.valueOf(resource.getId()));
                    servicePermissionDto.setPermissionId(String.valueOf(permission.getId()));
                    servicePermissionDto.setOperate(permission.getOperate());
                    serviceDtos.add(servicePermissionDto);
                }
            }
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(serviceDtos);
        return response;
    }
}
