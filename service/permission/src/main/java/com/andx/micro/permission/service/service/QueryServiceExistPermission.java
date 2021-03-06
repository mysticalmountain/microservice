package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-5-9.
 */
@Component
@com.andx.micro.api.core.Service(path = "/service/\\w+/noPermission", code = "queryServiceNoPermission", name = "查询服务是否有权限控制", module = "permission")
public class QueryServiceExistPermission extends GetSampleService<Response<Boolean>> {
    @Autowired
    @Qualifier("servicePermissionExistsHandler")
    private ServiceHandler<String, Boolean> serviceValidateHandler;
    private static Pattern pattern = Pattern.compile("/\\w+/");

    @Override
    public Response<Boolean> doService(Map<String, String> stringMap, String path) throws ServiceException {
        try {
            String tmp = path.substring(0, path.lastIndexOf("/"));
            String id = tmp.substring(tmp.lastIndexOf("/") + 1);
            Boolean exists = serviceValidateHandler.handle(id, null);
            Response<Boolean> response = new Response<>(Constant.MSG_SUCCESS, true);
            if (exists) {
                response.setData(Boolean.FALSE);
            } else {
                response.setData(Boolean.TRUE);
            }
            return response;
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
