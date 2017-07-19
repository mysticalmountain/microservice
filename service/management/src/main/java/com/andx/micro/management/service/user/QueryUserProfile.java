package com.andx.micro.management.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ProfileDto;
import com.andx.micro.management.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/user/\\w+/profile", code = "queryUserProfile", name = "查询用户摘要", module = "management")
public class QueryUserProfile extends GetComplexService<Response<ProfileDto>>{

    @Autowired
    private UserClient userClient;

    @Override
    protected Response<ProfileDto> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String userId = tmp.substring(tmp.lastIndexOf("/") + 1);
        HttpServletRequest request = context.getAttribute("httpServletRequest", HttpServletRequest.class);
        Long orgId = (Long) request.getSession().getAttribute("orgId");
        Response<List<UserDto>> res = userClient.queryOrgUsers(orgId, Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
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
        return userClient.queryUserProfile(Long.valueOf(userId), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
