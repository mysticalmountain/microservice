package com.andx.micro.user.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.dto.ProfileDto;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-4-15.
 */
@Component
@Service(path = "/users/\\w+/profiles", code = "queryUsersProfiles", name = "查询用户摘要", module = "用户")
public class QueryUsersProfiles extends GetSampleService<Response<ProfileDto>> {

    @Autowired
    private UserRepository userRepository;

    private static  final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<ProfileDto> doService(Map<String, String[]> stringMap, String path) throws ServiceException {
//        String path = stringMap.get("path")[0];
        String userId = getUserId(path);
        User user = userRepository.findOne(Long.valueOf(userId));
        Profile profile = user.getProfile();
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        Response<ProfileDto> response = new Response<>();
        response.setData(profileDto);
        return response;
    }

    private String getUserId(String path) {
        Matcher matcher = pattern.matcher(path);
        matcher.find(15);
        String temp = matcher.group();
        return temp.substring(0, temp.length() - 1);
    }
}
