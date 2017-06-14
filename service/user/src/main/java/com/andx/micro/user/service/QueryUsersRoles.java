package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-3-29.
 */
@Component
@Service(path = "/users/\\w+/roles", code = "queryUsersRoles", name = "查询用户角色", module = "用户")
public class QueryUsersRoles extends GetSampleService<Response<Set<RolebakDto>>> {

    @Autowired
    private UserRepository userRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<Set<RolebakDto>> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
//        String path = stringMap.get("path")[0];
        String userId = getUserId(path);
        User user = userRepository.findOne(Long.valueOf(userId));
        Iterator<Rolebak> rolebaks = user.getRoles().iterator();
        Set<RolebakDto> rolebakDtos = new HashSet<>();
        while (rolebaks.hasNext()) {
            Rolebak rolebak = rolebaks.next();
            RolebakDto rolebakDto = new RolebakDto();
            BeanUtils.copyProperties(rolebak, rolebakDto);
            rolebakDtos.add(rolebakDto);
        }
        Response<Set<RolebakDto>> response = new Response<>(true);
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
