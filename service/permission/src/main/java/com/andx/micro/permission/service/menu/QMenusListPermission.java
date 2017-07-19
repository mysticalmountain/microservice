package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.repository.MenuRepository;
import com.andx.micro.permission.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-6-21.
 */
@Component
@Service(path = "/menus/list/permission", code = "queryMenusListPermission", name = "查询菜单列表(有权限)", module = "permission")
public class QMenusListPermission extends GetSampleService<Response<List<MenuDto>>> {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected Response<List<MenuDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        Menu menu = menuRepository.findByName("root", new Sort(Sort.Direction.ASC, "od"));
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
            Resource resource = sonMenu.getResource();
            Permission permission = permissionRepository.findByResource(resource);
            if (permission != null) {
                MenuDto sonMenuDto = new MenuDto();
                sonMenuDto.setId(sonMenu.getId());
                sonMenuDto.setName(sonMenu.getName());
                sonMenuDto.setAction(sonMenu.getAction());
                sonMenuDto.setOd(sonMenu.getOd());
                sonMenuDto.setPid(sonMenu.getParent() == null ? null : sonMenu.getParent().getId());
                ResourceDto resourceDto = new ResourceDto();
                resourceDto.setId(resource.getId());
                sonMenuDto.setResourceDto(resourceDto);
                menuDtos.add(sonMenuDto);
            }
            fetch(menuDtos, sonMenu.getSons());
        }
    }
}
