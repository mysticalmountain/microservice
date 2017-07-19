package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
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
@Service(path = "/user/\\w+/profile", code = "queryUsersProfiles", name = "查询用户摘要", module = "user")
public class QueryUserProfile extends GetSampleService<Response<ProfileDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<ProfileDto> doService(Map<String, String> stringMap, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String userId = tmp.substring(tmp.lastIndexOf("/") + 1);
        User user = userRepository.findOne(Long.valueOf(userId));
        Profile profile = user.getProfile();
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(profileDto);
        return response;
    }

}
