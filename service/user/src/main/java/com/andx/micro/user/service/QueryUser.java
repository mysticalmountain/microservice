package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-3-28.
 */
@Component
@Service(path = "/users/\\w+", code = "queryUserById", name = "按ID查询用户", module = "用户")
public class QueryUser extends GetSampleService<Response<UserDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<UserDto> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
//        String path = stringMap.get("path")[0];
        String userId = path.substring(path.lastIndexOf("/") + 1, path.length());
        User user = userRepository.findOne(Long.parseLong(userId));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        Response<UserDto> response = new Response<UserDto>(true);
        response.setData(userDto);
        return response;
    }
}
