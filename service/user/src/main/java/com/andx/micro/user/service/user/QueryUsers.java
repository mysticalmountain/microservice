package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-4-14.
 */
@Component
@Service(path = "/users", code = "users", name = "查询用户" , module = "user")
public class QueryUsers extends GetSampleService<Response<List<UserDto>>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<List<UserDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(userDtos);
        return response;
    }

}
