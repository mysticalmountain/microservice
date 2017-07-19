package com.andx.micro.management.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/menu/\\w+", code = "editMenu", method = MethodType.PUT, name = "编辑菜单", module = "management")
public class EditMenu extends PutComplexService<Request<MenuDto>, Response<MenuDto>>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<MenuDto> doService(Request<MenuDto> menuDtoRequest, String path, ServiceContext context) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        menuDtoRequest.setChannelId(Constant.MODULE);
        return permissionClient.editMenu(Long.valueOf(id), menuDtoRequest);
    }
}
