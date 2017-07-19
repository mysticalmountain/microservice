package com.andx.micro.permission.service.permission;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.core.validator.PermissionValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
@Service(path = "/owner/\\w+/resource/\\w+", code = "queryOwnerPermission", name = "查询用户/渠道权限", module = "permission")
public class QueryOwnerResource extends GetComplexService<Response> {

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Autowired
    @Qualifier("channelValidateHandler")
    private ServiceHandler<PermissionValidatorDto, Boolean> channelValidateHandler;

    @Autowired
    @Qualifier("roleValidateHandler")
    private ServiceHandler<PermissionValidatorDto, Boolean> roleValidateHandler;

    @Override
    protected Response doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        tmp = tmp.substring(0, tmp.lastIndexOf("/"));
        String ownerId = tmp.substring(tmp.lastIndexOf("/") + 1);
        String serviceCode = path.substring(path.lastIndexOf("/") + 1);
        PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
        permissionValidatorDto.setServiceCode(serviceCode);
        permissionValidatorDto.setOwnerId(ownerId);
        try {
            Boolean channelRes = channelValidateHandler.handle(permissionValidatorDto, context);
            if (channelRes != null && channelRes == true) {
                return new Response(Constant.MSG_SUCCESS, true);
            }
//            Boolean roleRes = roleValidateHandler.handle(permissionValidatorDto, context);
//            if (roleRes != null && roleRes == true) {
//                return new Response(Constant.MSG_SUCCESS, true);
//            }
            return new Response(Constant.MSG_FAIL, false);
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private String getOwnerId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(16);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }

    private String getServiceCode(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }
}
