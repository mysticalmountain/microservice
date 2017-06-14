package com.andx.micro.permission.service.role;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@Service(path = "/roles/\\w+", code = "deleteRole", method = MethodType.DELETE, name = "删除角色", module = "权限")
public class DeleteRoles extends DeleteSampleService<Response> {

    @Autowired
    private RoleRepository roleRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response doService(String path) throws ServiceException {
        String roleId = path.substring(15);
        roleRepository.delete(Long.valueOf(roleId));
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }

    private String getRoleId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(15);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }
}
