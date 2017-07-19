package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.permission.client.OrgDto;
import com.andx.micro.permission.client.UserClient;
import com.andx.micro.permission.config.Constant;
import com.andx.micro.permission.dto.role.RoleDto;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/org/\\w+/roles", code = "queryOrgRoles", name = "查询某机构的角色", module = "permission")
public class QueryOrgRoles extends GetSampleService<Response<List<RoleDto>>> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserClient userClient;

    @Override
    protected Response<List<RoleDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String orgId = tmp.substring(tmp.lastIndexOf("/") + 1);
        Response<List<OrgDto>> res = userClient.getListOrgs(Long.valueOf(orgId), prams.get(Constant.KEY_CHANNEL_ID), prams.get(Constant.KEY_REQUEST_ID));
        List<OrgDto> orgDtos = res.getData();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (OrgDto orgDto : orgDtos) {
            List<Role> roles = roleRepository.findByOrgId(orgDto.getId());
            for (Role role : roles) {
                RoleDto roleDto = new RoleDto();
                BeanUtils.copyProperties(role, roleDto);
                roleDtos.add(roleDto);
            }
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(roleDtos);
        return response;
    }
}
