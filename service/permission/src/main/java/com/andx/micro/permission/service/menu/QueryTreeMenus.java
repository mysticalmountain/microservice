package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.repository.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-6-17.
 */
@Component
@Service(path = "/tree-menus", code = "queryMenusTree", name = "查询菜单树", module = "permission")
public class QueryTreeMenus extends GetSampleService<Response<MenuDto>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected Response<MenuDto> doService(Map<String, String> prams, String path) throws ServiceException {
        String menuId = null;
        if (prams.get("id") != null) {
            menuId = prams.get("id");
        }
        Menu menu = null;
        if (menuId == null) {
            menu = menuRepository.findByName("root");
        } else {
            menu = menuRepository.findOne(Long.valueOf(menuId));
            if (menu == null) {
                throw new ServiceException(String.format("菜单[%s]不存在", menuId));
            }
        }
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setAction(menu.getAction());
        menuDto.setOd(menu.getOd());
        fetch(menuDto, menu);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(menuDto);
        return response;
    }

    private void fetch(MenuDto menuDto, Menu menu) {
        List<Menu> sonMenus = menu.getSons();
        for (Menu sonMenu : sonMenus) {
            MenuDto sonMenuDto = new MenuDto();
            sonMenuDto.setId(sonMenu.getId());
            sonMenuDto.setName(sonMenu.getName());
            sonMenuDto.setAction(sonMenu.getAction());
            sonMenuDto.setOd(sonMenu.getOd());
            menuDto.getSons().add(sonMenuDto);
            fetch(sonMenuDto, sonMenu);
        }
    }
}
