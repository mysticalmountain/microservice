package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-6-20.
 */
@Component
@Service(path = "/menu/\\w+", code = "editMenu", method = MethodType.PUT, name = "编辑菜单", module = "permission")
public class EditMenu extends PutSampleService<Request<MenuDto>, Response<Boolean>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected Response<Boolean> doService(Request<MenuDto> menuDtoRequest, String path) throws ServiceException {
        String menuId = path.substring(path.lastIndexOf("/") + 1);
        MenuDto menuDto = menuDtoRequest.getData();
        Menu parentMenu = menuRepository.findOne(menuDto.getPid());
        if (parentMenu == null) {
            throw new ServiceException(String.format("父菜单%s不存在", menuDto.getPid()));
        }
        Menu menu = menuRepository.findOne(Long.valueOf(menuId));
        if (menu ==  null) {
            throw new ServiceException(String.format("菜单%s不存在", menuId));
        }
        menu.setName(menuDto.getName());
        menu.setAction(menuDto.getAction());
        menu.setOd(menuDto.getOd());
        menu.setAction(menuDto.getAction());
        menuRepository.save(menu);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
