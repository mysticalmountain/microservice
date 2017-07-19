package com.andx.micro.management.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/menu", code = "newMenu", method = MethodType.POST, name = "新建菜单", module = "management")
public class NewMenu extends PostComplexService<Request<MenuDto>, Response<MenuDto>>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<MenuDto> doService(Request<MenuDto> menuDtoRequest, ServiceContext context) throws ServiceException {
        menuDtoRequest.setChannelId(Constant.MODULE);
        return permissionClient.newMenu(menuDtoRequest);
    }
}
