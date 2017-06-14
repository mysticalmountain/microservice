package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/roles/\\w+/permissions", code = "queryRolePermissions", name = "查询用户角色", module = "权限")
public class QueryRolesPermissions extends GetSampleService<Response<Set<PermissionDto>>> {

    @Autowired
    private RoleRepository roleRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<Set<PermissionDto>> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
//        String path = stringMap.get("path")[0];
        String roleId = getRoleId(path);
        Role role = roleRepository.findOne(Long.valueOf(roleId));
        Iterator<Permission> permissions = role.getPermissions().iterator();
        Set<PermissionDto> permissionDtos = new HashSet<>();
        while (permissions.hasNext()) {
            Permission permission = permissions.next();
            PermissionDto permissionDto = new PermissionDto();
            BeanUtils.copyProperties(permission, permissionDto);
            permissionDtos.add(permissionDto);
        }
        Response<Set<PermissionDto>> response = new Response<>(true);
        response.setData(permissionDtos);
        return response;
    }

    private String getRoleId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(15);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }
}
