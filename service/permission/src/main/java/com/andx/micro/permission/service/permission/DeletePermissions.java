package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import com.andx.micro.permission.repository.PermissionRepository;
import com.andx.micro.permission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by andongxu on 17-5-10.
 */
@Component
@Service(path = "/permissions/\\w+", code = "deletePermission", method = MethodType.GET, name = "删除权限", module = "权限")
public class DeletePermissions extends DeleteSampleService<Response<Boolean>> {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected Response<Boolean> doService(String path) throws ServiceException {
        Permission permission = permissionRepository.findOne(Long.valueOf(getPermissionId(path)));
        Iterator<Role> roles = permission.getRoles().iterator();
        while (roles.hasNext()) {
            Role role = roles.next();
            role.getPermissions().remove(permission);
            roleRepository.save(role);
        }
        permissionRepository.delete(permission);
        Response response = Constant.RESPONSE_SUCCESS.clone();
        response.setSuccess(true);
        return response;
    }

    private String getPermissionId(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }
}
