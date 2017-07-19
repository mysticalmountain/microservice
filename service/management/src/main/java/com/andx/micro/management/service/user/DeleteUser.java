package com.andx.micro.management.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteComplexService;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.OrgDto;
import com.andx.micro.management.dto.UserDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
@Service(path = "/user/\\w+", code = "deleteUser", method = MethodType.DELETE, name = "刪除用戶", module = "management")
public class DeleteUser extends DeleteComplexService<Response> {

    @Autowired
    private UserClient userClient;

    @Override
    protected Response doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        String userId = path.substring(path.lastIndexOf("/") + 1);
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
        return userClient.deleteUser(Long.valueOf(userId), Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
