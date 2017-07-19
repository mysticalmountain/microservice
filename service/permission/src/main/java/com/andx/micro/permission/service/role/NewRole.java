package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GenericSampleService;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.role.RoleDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/role", code = "newRole", method = MethodType.POST, name = "新建角色", module = "permission")
public class NewRole extends PostSampleService<Request<RoleDto>, Response<RoleDto>> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected Response<RoleDto> doService(Request<RoleDto> roleDtoRequest) throws ServiceException {
        RoleDto roleDto = roleDtoRequest.getData();
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        Iterator<String> permissionIds = roleDto.getPermissionIds().iterator();
        Set<Permission> permissions = new HashSet<>();
        while (permissionIds.hasNext()) {
            String permissionId = permissionIds.next();
            Permission permission = permissionRepository.findOne(Long.valueOf(permissionId));
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        role = roleRepository.save(role);
        return new Response<>(Constant.MSG_SUCCESS, true);
    }
}
