package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.repository.MenuRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-6-21.
 */
@Component
@Service(path = "/list-menus", code = "querytListMenus", name = "查询菜单列表", module = "permission")
public class QueryListMenus extends GetSampleService<Response<List<MenuDto>>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Response<List<MenuDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String menuId = null;
        if (prams.get("id") != null) {
            menuId = prams.get("id");
        }
        Menu menu = null;
        if (menuId == null) {
            menuRepository.findByName("root");
        } else {
            menu = menuRepository.findOne(Long.valueOf(menuId));
            if (menu == null) {
                throw new ServiceException(String.format("菜单[%s]不存在", menuId));
            }
        }
        List<MenuDto> menuDtos = new ArrayList<MenuDto>();
        List<Menu> root = new ArrayList<>();
        MenuDto menuDto = new MenuDto();
        root.add(menu);
        fetch(menuDtos, root);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(menuDtos);
        return response;
    }

    private void fetch(List menuDtos, List<Menu> menus) {
        for (Menu sonMenu : menus) {
            MenuDto sonMenuDto = new MenuDto();
            sonMenuDto.setId(sonMenu.getId());
            sonMenuDto.setName(sonMenu.getName());
            sonMenuDto.setAction(sonMenu.getAction());
            sonMenuDto.setOd(sonMenu.getOd());
            sonMenuDto.setPid(sonMenu.getParent() == null ? null : sonMenu.getParent().getId());
            menuDtos.add(sonMenuDto);
            fetch(menuDtos, sonMenu.getSons());
        }
    }
}
