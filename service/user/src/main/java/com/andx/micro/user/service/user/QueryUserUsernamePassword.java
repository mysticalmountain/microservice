package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-8.
 */
@Component
@Service(path = "/user/username/\\w+/password/\\w+", code = "queryUserUsernamePassword", name = "查询用户ID", module = "user")
public class QueryUserUsernamePassword extends GetSampleService<Response<Long>> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    protected Response<Long> doService(Map<String, String> prams, String path) throws ServiceException {
        String password = path.substring(path.lastIndexOf("/") + 1);
        String tmp = path.substring(0, path.lastIndexOf("/"));
        tmp = tmp.substring(0, tmp.lastIndexOf("/"));
        String username = tmp.substring(tmp.lastIndexOf("/") + 1);
        Authority authority = authorityRepository.findByAccountNo(username);
        if (authority == null) {
            throw new ServiceException(String.format("用户不存在", username));
        }
        if (!authority.getPassword().equals(password)) {
            throw new ServiceException("密码错误");
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(authority.getUser().getId());
        return response;
    }
}
