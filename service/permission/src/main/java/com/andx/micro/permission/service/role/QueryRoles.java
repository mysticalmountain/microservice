package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.role.RoleDto;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/roles", code = "queryRoles", name = "查询角色", module = "permission")
public class QueryRoles extends GetSampleService<Response<List<RoleDto>>> {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Response<List<RoleDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            roleDtos.add(roleDto);
        }
        Response<List<RoleDto>> response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(roleDtos);
        return response;
    }

}
