package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.dto.AuthorityDto;
import com.andx.micro.user.model.Authority;
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
@Service(path = "/users/\\w+/authorities", code = "queryUserAuthorities", name = "查询用户权限", module = "用户")
public class QueryUsersAuthorities extends GetSampleService<Response> {

    @Autowired
    private UserRepository userRepository;

    private static  final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response doService(Map<String, String[]> prams, String path) throws ServiceException {
//        String path = prams.get("path")[0];
        String userId = getUserId(path);
        User user = userRepository.findOne(Long.valueOf(userId));
        Iterator<Authority> authorities =user.getAuthorities().iterator();
        Set<AuthorityDto> authorityDtos = new HashSet<>();
        while (authorities.hasNext()){
            Authority authority = authorities.next();
            AuthorityDto authorityDto = new AuthorityDto();
            BeanUtils.copyProperties(authority, authorityDto);
            authorityDtos.add(authorityDto);
        }
        Response<Set<AuthorityDto>> response = new Response<>(true);
        response.setData(authorityDtos);
        return response;
    }

    private String getUserId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(15);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }
}
