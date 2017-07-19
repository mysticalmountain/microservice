package com.andx.micro.management.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutComplexService;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.EditUserDto;
import com.andx.micro.management.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/user/\\w+", method = MethodType.PUT, code = "editUser", name = "编辑用户", module = "management")
public class EditUser extends PutComplexService<Request<EditUserDto>, Response<EditUserDto>> {

    @Autowired
    private UserClient userClient;

    @Override
    protected Response<EditUserDto> doService(Request<EditUserDto> userDtoRequest, String path, ServiceContext context) throws ServiceException {
        String userId = path.substring(path.lastIndexOf("/") + 1);
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        Response<List<UserDto>> res = userClient.queryOrgUsers(orgId, Constant.MODULE, userDtoRequest.getRequestId());
        if (res.getSuccess() == false) {
            throw new ServiceException(res.getErrorMessage());
        }
        List<UserDto> userDtos = res.getData();
        boolean exist = false;
        for (UserDto userDto : userDtos) {
            if (userDto.getId().intValue() == Long.valueOf(userId).intValue()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new ServiceException(String.format("无权限操作用户[%s]", userId));
        }
        userDtoRequest.setChannelId(Constant.MODULE);
        Response response = userClient.editUser1(Long.valueOf(userId), userDtoRequest);
        return response;
    }
}
