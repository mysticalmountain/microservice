package com.andx.micro.permission.service.permission.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务是否有权限控制
 * Created by andongxu on 17-5-9.
 */
@Component
public class ServicePermissionExistsHandler extends GenericServiceHandler<String, Boolean> {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public Boolean doHandle(String s, ServiceContext context) throws HandlerException {
        com.andx.micro.permission.model.Service service = serviceRepository.findByCode(s);
        List<Permission> permissions = permissionRepository.findAll();
        boolean exists = false;
        for (Permission permission : permissions) {
            if (permission.getResource().getId() == service.getResource().getId()) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public Boolean support(String s, ServiceContext context) {
        return true;
    }
}
