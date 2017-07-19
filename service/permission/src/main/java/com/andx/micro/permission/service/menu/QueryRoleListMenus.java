package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.menu.MenuDto;
import com.andx.micro.permission.model.*;
import com.andx.micro.permission.repository.MenuRepository;
import com.andx.micro.permission.repository.RoleRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-6.
 */
@Component
@Service(path = "/role/\\w+/list-menus", code = "queryRoleListMenus", name = "查询角色的菜单列表", module = "permission")
public class QueryRoleListMenus extends GetSampleService<Response<List<MenuDto>>> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected Response<List<MenuDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String roleId = tmp.substring(tmp.lastIndexOf("/") + 1);
        List<MenuDto> menuDtos = new ArrayList<>();
        Role role = roleRepository.findOne(Long.valueOf(roleId));
        Iterator<Permission> permissionIterator = role.getPermissions().iterator();
        while (permissionIterator.hasNext()) {
            Permission permission = permissionIterator.next();
            Resource resource = permission.getResource();
            if (resource.getResourceType() == ResourceType.MENU) {
                Menu menu = menuRepository.findByResource(resource);
                MenuDto menuDto = new MenuDto();
                menuDto.setId(menu.getId());
                menuDto.setName(menu.getName());
                menuDto.setOd(menu.getOd());
                menuDto.setAction(menu.getAction());
                menuDto.setPid(menu.getParent() != null ? menu.getParent().getId() : null);
                menuDtos.add(menuDto);
            }
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(menuDtos);
        return response;
    }
}
