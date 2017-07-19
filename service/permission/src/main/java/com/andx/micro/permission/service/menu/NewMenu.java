package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.andx.micro.permission.repository.MenuRepository;
import com.andx.micro.permission.repository.ResourceRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-6-20.
 */
@Component
@Service(path = "/menu", code = "newMenu", method = MethodType.POST, name = "新建菜单", module = "permission")
public class NewMenu extends PostSampleService<Request<MenuDto>, Response<Boolean>> {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    protected Response<Boolean> doService(Request<MenuDto> menuDtoRequest) throws ServiceException {
        Menu menu = new Menu();
        MenuDto menuDto = menuDtoRequest.getData();
        Menu parentMenu = menuRepository.findOne(menuDto.getPid());
        if (parentMenu == null) {
            throw new ServiceException(String.format("父菜单%s不存在", menuDto.getPid()));
        }
        BeanUtils.copyProperties(menuDto, menu);
        Resource resource = new Resource();
        resource.setResourceType(ResourceType.MENU);
        resourceRepository.save(resource);
        menu.setParent(parentMenu);
        menu.setResource(resource);
        menuRepository.save(menu);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
