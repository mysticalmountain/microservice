package com.andx.micro.user.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.core.service.PutComplexService;
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
@Service(path = "/user/\\w+", method = MethodType.PUT, code = "editUser", name = "编辑用户", module = "user")
public class EditUser extends PutComplexService<Request<EditUserDto>, Response<EditUserDto>> {

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
        String userId = path.substring(path.lastIndexOf("/") + 1);
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
            Response response = new Response(Constant.MSG_SUCCESS, true);
            return response;
        } catch (HandlerException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
