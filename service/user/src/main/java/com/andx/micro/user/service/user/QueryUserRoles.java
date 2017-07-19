package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-3-29.
 */
@Component
@Service(path = "/user/\\w+/roles", code = "queryUsersRoles", name = "查询用户角色", module = "user")
public class QueryUserRoles extends GetSampleService<Response<List<RolebakDto>>> {

    @Autowired
    private UserRepository userRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<List<RolebakDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String userId = tmp.substring(tmp.lastIndexOf("/") + 1);
        User user = userRepository.findOne(Long.valueOf(userId));
        Iterator<Rolebak> rolebaks = user.getRoles().iterator();
        List<RolebakDto> rolebakDtos = new ArrayList<>();
        while (rolebaks.hasNext()) {
            Rolebak rolebak = rolebaks.next();
            RolebakDto rolebakDto = new RolebakDto();
            BeanUtils.copyProperties(rolebak, rolebakDto);
            rolebakDtos.add(rolebakDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(rolebakDtos);
        return response;
    }

    private String getUserId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(15);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }
}
