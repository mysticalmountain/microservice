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

import java.util.Map;

/**
 * Created by andongxu on 17-4-19.
 */
@Component
@Service(path = "/role/\\w+", code = "queryRole", name = "按ID查询角色", module = "permission")
public class QueryRole extends GetSampleService<Response<RoleDto>> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected Response<RoleDto> doService(Map<String, String> prams, String path) throws ServiceException {
        String roleId = path.substring(path.lastIndexOf("/") + 1, path.length());
        Role role = roleRepository.findOne(Long.valueOf(roleId));
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        Response<RoleDto> response = new Response<>(Constant.MSG_SUCCESS, true);
        response.setData(roleDto);
        return response;
    }
}
