package com.andx.micro.management.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/tree-menus", code = "queryTreeMenus", name = "查询菜单树", module = "management")
public class QueryTreeMenus extends GetComplexService<Response<MenuDto>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<MenuDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        return permissionClient.queryTreeMenus(prams.get("id"), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
