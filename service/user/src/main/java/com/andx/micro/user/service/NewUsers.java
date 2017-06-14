package com.andx.micro.user.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by andongxu on 17-3-29.
 */
@Component
@Service(path = "/users", method = MethodType.POST, code = "newUser", name = "新建用户", module = "用户")
public class NewUsers extends PostComplexService<Request<EditUserDto>, Response<EditUserDto>> {

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
    @Transactional
    protected Response<EditUserDto> doService(Request<EditUserDto> editUserDtoRequest, ServiceContext context) throws ServiceException {
        EditUserDto editUserDto = editUserDtoRequest.getData();
        UserDto userDto = editUserDto.getUserDto();
        ProfileDto profileDto = editUserDto.getProfileDto();
        AuthorityDto authorityDto = editUserDto.getAuthorityDto();
        Set<RolebakDto> rolebakDtos = editUserDto.getRoleDtos();
        try {
            userDto = editUserHandler.handle(userDto, context);
            context.setAttribute("userId", userDto.getId());
            editProfileHandler.handle(profileDto, context);
            editAuthorityHandler.handle(authorityDto, context);
            editRoleHandler.handle(rolebakDtos, context);
            return Constant.RESPONSE_SUCCESS.clone();
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
