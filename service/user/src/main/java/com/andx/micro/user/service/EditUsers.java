package com.andx.micro.user.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.PatchComplexService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 17-3-29.
 */
@Component
@Service(path = "/users/\\w+", method = MethodType.PATCH, code = "editUser", name = "编辑用户", module = "用户")
public class EditUsers extends PatchComplexService<Request<EditUserDto>, Response<EditUserDto>> {

    @Autowired
    @Qualifier("editUserHandler")
    private ServiceHandler<UserDto, UserDto> editUserHandler;

    @Autowired
    @Qualifier("editProfileHandler")
    private ServiceHandler<ProfileDto, ProfileDto> editProfileHandler;

    @Autowired
    @Qualifier("editAuthorityHandler")
    private ServiceHandler<AuthorityDto, AuthorityDto> editAuthorityHandler;

    @Autowired
    @Qualifier("editRoleHandler")
    private ServiceHandler<Set<RolebakDto>, Boolean> editRoleHandler;


    @Override
    public void init() {

    }

    @Override
    protected Response<EditUserDto> doService(Request<EditUserDto> editUserDtoRequest, String path, ServiceContext context) throws ServiceException {
        String userId = getUserId(path);
        context.setAttribute("userId", Long.valueOf(userId));
        EditUserDto editUserDto = editUserDtoRequest.getData();
        UserDto userDto = editUserDto.getUserDto();
        ProfileDto profileDto = editUserDto.getProfileDto();
        AuthorityDto authorityDto = editUserDto.getAuthorityDto();
        Set<RolebakDto> rolebakDtos = editUserDto.getRoleDtos();
        try {
            userDto = editUserHandler.handle(userDto, context);
            editProfileHandler.handle(profileDto, context);
            editAuthorityHandler.handle(authorityDto, context);
            editRoleHandler.handle(rolebakDtos, context);
            return Constant.RESPONSE_SUCCESS.clone();
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private String getUserId(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }
}
