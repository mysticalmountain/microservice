package com.andx.micro.management.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/menu/\\w+", code = "deleteMenu", method = MethodType.DELETE, name = "删除菜单", module = "management")
public class DeleteMenu extends DeleteComplexService<Response>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        return permissionClient.deleteMenu(Long.valueOf(id), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
