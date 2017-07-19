package com.andx.micro.management.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.EditUserDto;
import com.andx.micro.management.dto.OrgDto;
import com.andx.micro.management.dto.UserDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/user", method = MethodType.POST, code = "newUser", name = "新建用户", module = "management")
public class NewUser extends PostComplexService<Request<EditUserDto>, Response<EditUserDto>>{

    @Autowired
    private UserClient userClient;

    @Override
    protected Response<EditUserDto> doService(Request<EditUserDto> userDtoRequest, ServiceContext context) throws ServiceException {
        Long userOrgId = userDtoRequest.getData().getUserDto().getOrgId();
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        Response<List<OrgDto>> res = userClient.queryListOrgs(orgId, Constant.MODULE, userDtoRequest.getRequestId());
        if (res.getSuccess() == false) {
            throw new ServiceException(res.getErrorMessage());
        }
        List<OrgDto> orgDtos = res.getData();
        boolean exist = false;
        for (OrgDto orgDto : orgDtos) {
            if (orgDto.getId().intValue() == userOrgId.intValue()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new ServiceException(String.format("无权限操作机构[%s]", userOrgId));
        }
        userDtoRequest.setChannelId(Constant.MODULE);
        return userClient.newUser(userDtoRequest);
    }
}
