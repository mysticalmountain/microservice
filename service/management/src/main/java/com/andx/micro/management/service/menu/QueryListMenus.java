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

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/list-menus", code = "querytListMenus", name = "查询菜单列表", module = "management")
public class QueryListMenus extends GetComplexService<Response<List<MenuDto>>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<List<MenuDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        return permissionClient.queryListMenus(prams.get("id"), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
