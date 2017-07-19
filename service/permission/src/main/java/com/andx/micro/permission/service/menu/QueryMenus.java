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
@Service(path = "/menus", code = "queryMenus", name = "查询菜单", module = "permission")
public class QueryMenus extends GetSampleService<Response<MenuDto>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected Response<MenuDto> doService(Map<String, String> prams, String path) throws ServiceException {
        Menu menu = menuRepository.findByName("root", new Sort(Sort.Direction.ASC, "od"));
        MenuDto menuDto = new MenuDto();
        fetch(menuDto, menu);
        BeanUtils.copyProperties(menu, menuDto);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(menuDto);
        return response;
    }

    private void fetch(MenuDto menuDto, Menu menu) {
        Iterator<Menu> menus = menu.getSons().iterator();
        while (menus.hasNext()) {
            Menu sonMenu = menus.next();
            MenuDto sonMenuDto = new MenuDto();
            BeanUtils.copyProperties(sonMenu, sonMenuDto);
            menuDto.getSons().add(sonMenuDto);
            menuDto.getSons().sort(new Comparator<MenuDto>() {
                @Override
                public int compare(MenuDto o1, MenuDto o2) {
                    return o1.getOd().intValue() - o2.getOd().intValue();
                }
            });
            fetch(sonMenuDto, sonMenu);
        }
    }
}
