package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PatchSampleService;
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
@Service(path = "/roles/\\w+", code = "editRole", method = MethodType.PATCH, name = "编辑角色", module = "权限")
public class EditRoles extends PatchSampleService<Request<RoleDto>, Response<RoleDto>> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response<RoleDto> doService(Request<RoleDto> roleDtoRequest, String path) throws ServiceException {
        RoleDto roleDto = roleDtoRequest.getData();
        String roleId = getRoleId(path);
        Role role = roleRepository.findOne(Long.valueOf(roleId));
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
        Response<RoleDto> response = new Response<>(Constant.MSG_SUCCESS, true);
        response.setData(roleDto);
        return response;
    }

    private String getRoleId(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }
}
