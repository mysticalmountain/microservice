package com.andx.micro.permission.service.menu;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.repository.MenuRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-6-20.
 */
@Component
@Service(path = "/menu/\\w+", code = "deleteMenu", method = MethodType.DELETE, name = "删除菜单", module = "permission")
public class DeleteMenu extends DeleteSampleService<Response<Boolean>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected Response<Boolean> doService(Map<String, String> prams, String path) throws ServiceException {
        String menuId = path.substring(path.lastIndexOf("/") + 1);
        Menu menu = menuRepository.findOne(Long.valueOf(menuId));
        if (menu.getSons().size() > 0) {
            throw new ServiceException(String.format("存在子菜单，不允许删除"));
        }
        menuRepository.delete(menu);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
