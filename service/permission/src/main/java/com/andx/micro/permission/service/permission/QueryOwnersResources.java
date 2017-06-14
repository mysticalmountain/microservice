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
@Service(path = "/owners/\\w+/resources/\\w+", code = "queryOwnersPermissions", name = "查询用户/渠道权限", module = "权限")
public class QueryOwnersResources extends GetComplexService<Response> {

//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private ChannelRepository channelRepository;
//    @Autowired
//    private ServiceRepository serviceRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Autowired
    @Qualifier("channelValidateHandler")
    private ServiceHandler<PermissionValidatorDto, Boolean> channelValidateHandler;

    @Autowired
    @Qualifier("roleValidateHandler")
    private ServiceHandler<PermissionValidatorDto, Boolean> roleValidateHandler;

    @Override
    protected Response doService(Map<String, String[]> prams, String path, ServiceContext context) throws ServiceException {
        String ownerId = getOwnerId(path);
        String serviceCode = getServiceCode(path);
        PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
        permissionValidatorDto.setServiceCode(serviceCode);
        permissionValidatorDto.setOwnerId(ownerId);
        try {
            Boolean channelRes = channelValidateHandler.handle(permissionValidatorDto, context);
            if (channelRes != null && channelRes == true) {
                return Constant.RESPONSE_SUCCESS.clone();
            }
            Boolean roleRes = roleValidateHandler.handle(permissionValidatorDto, context);
            if (roleRes != null && roleRes == true) {
                return Constant.RESPONSE_SUCCESS.clone();
            }
            return Constant.RESPONSE_FAIL.clone();
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


//    @Override
//    protected Response doService(Map<String, String[]> prams, String path) throws ServiceException {
//        public Response doService(Map<String, String[]> prams, ServiceContext context, Object... args) throws ServiceException {
//        String ownerId = getOwnerId(path);
//        String serviceCode = getServiceCode(path);
//        PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
//        permissionValidatorDto.setServiceCode(serviceCode);
//        permissionValidatorDto.setOwnerId(ownerId);
//        channelValidateHandler.handle(permissionValidatorDto);
//        if (isChannel(ownerId)) {
//            return processChannel(ownerId, serviceCode);
//        } else if (isRole(ownerId)) {
//            return processRole(ownerId, serviceCode);
//        } else {
//            throw new ServiceException("owner not found");
//        }
//    }

//    protected Response processChannel(String ownerId, String serviceCode) {
//        Channel channel = channelRepository.findByCode(ownerId);
//        Set<Permission> permissionSet = channel.getPermissions();
//        Long resourceId = serviceRepository.findByCode(serviceCode).getResource().getId();
//        Long count = permissionSet.stream().filter(p -> p.getResource().getId().equals(resourceId)).count();
//        Response response = new Response();
//        if (count > 0) {
//            response.setSuccess(true);
//        } else {
//            response.setSuccess(false);
//        }
//        return response;
//    }

//    protected Response processRole(String ownerId, String serviceCode) {
//        Role role = roleRepository.findOne(Long.valueOf(ownerId));
//        Set<Permission> permissionSet = role.getPermissions();
//        Long resourceId = serviceRepository.findByCode(serviceCode).getResource().getId();
//        Long count = permissionSet.stream().filter(p -> p.getResource().getId() == resourceId).count();
//        Response response = new Response();
//        if (count > 0) {
//            response.setSuccess(true);
//        } else {
//            response.setSuccess(false);
//        }
//        return response;
//    }


//    private boolean isRole(String ownerId) {
//        Role role = roleRepository.findOne(Long.valueOf(ownerId));
//        return role != null;
//    }

//    private boolean isChannel(String ownerId) {
//        Channel channel = channelRepository.findOne(Long.valueOf(ownerId));
//        return channel != null;
//    }

}
