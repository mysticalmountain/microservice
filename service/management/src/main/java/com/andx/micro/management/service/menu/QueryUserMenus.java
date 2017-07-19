package com.andx.micro.management.service.menu;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-6.
 */
@Component
@Service(path = "/user-menus", code = "queryUserMenus", name = "查询用户可见菜单", module = "management")
public class QueryUserMenus extends GetComplexService<Response<MenuDto>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<MenuDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        List<Long> roleIds = (List<Long>) request.getSession().getAttribute("roleIds");
        if (roleIds == null || roleIds.size() == 0) {
            throw new ServiceException("获取角色失败");
        }
        List<MenuDto> menuDtos = new ArrayList<>();
        for (Long roleId : roleIds) {
            Response<List<MenuDto>> response = permissionClient.queryRoleListMenus(roleId, com.andx.micro.management.config.Constant.MODULE, prams.get(com.andx.micro.management.config.Constant.KEY_REQUEST_ID));
            for (MenuDto menuDto : response.getData()) {
                if (!contains(menuDtos, menuDto)) {
                    menuDtos.add(menuDto);
                }
            }
        }
        Response<MenuDto> res = permissionClient.queryTreeMenus(null, com.andx.micro.management.config.Constant.MODULE, prams.get(com.andx.micro.management.config.Constant.KEY_REQUEST_ID));
        MenuDto menuDto = res.getData();
        fetch(menuDto, menuDtos);
        Response response = Constant.RESPONSE_SUCCESS;
        response.setData(menuDto);
        return response;
    }

    private boolean contains(List<MenuDto> menuDtos, MenuDto menuDto) {
        for (MenuDto md : menuDtos) {
            return md.getId() == menuDto.getId();
        }
        return false;
    }

    private void fetch(MenuDto menuDto, List<MenuDto> menuDtos) {
        Iterator<MenuDto> sonMenus = menuDto.getSons().iterator();
        while (sonMenus.hasNext()) {
            MenuDto sonMenu = sonMenus.next();
            boolean exists = false;
            for (MenuDto md : menuDtos) {
                if (md.getId().intValue() == sonMenu.getId().intValue()) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                sonMenus.remove();
            } else {
                fetch(sonMenu, menuDtos);
            }
        }
    }

}
