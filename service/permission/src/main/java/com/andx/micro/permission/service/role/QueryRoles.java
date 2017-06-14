package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.permission.dto.role.RoleDto;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/roles", code = "queryRoles", name = "查询角色", module = "权限")
public class QueryRoles extends GetSampleService<Response<Set<RoleDto>>> {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Response<Set<RoleDto>> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
        List<Role> roles = roleRepository.findAll();
        Set<RoleDto> roleDtos = new HashSet<>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            roleDtos.add(roleDto);
        }
        Response<Set<RoleDto>> response = new Response<>(true);
        response.setData(roleDtos);
        return response;
    }

}
