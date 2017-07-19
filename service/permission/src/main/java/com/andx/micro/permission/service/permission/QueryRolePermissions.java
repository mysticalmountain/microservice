package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/role/\\w+/permissions", code = "queryRolePermissions", method = MethodType.GET, name = "查询用户角色", module = "permission")
public class QueryRolePermissions extends GetSampleService<Response<List<PermissionDto>>> {

    @Autowired
    private RoleRepository roleRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<List<PermissionDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String roleId = tmp.substring(tmp.lastIndexOf("/") + 1);
        Role role = roleRepository.findOne(Long.valueOf(roleId));
        Iterator<Permission> permissions = role.getPermissions().iterator();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        while (permissions.hasNext()) {
            Permission permission = permissions.next();
            PermissionDto permissionDto = new PermissionDto();
            BeanUtils.copyProperties(permission, permissionDto);
            permissionDtos.add(permissionDto);
        }
        Response<List<PermissionDto>> response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(permissionDtos);
        return response;
    }
}
