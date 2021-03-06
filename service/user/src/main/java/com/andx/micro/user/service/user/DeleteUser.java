package com.andx.micro.user.service.user;

//import com.andx.micro.api.core.Service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.ProfileRepository;
import com.andx.micro.user.repository.RolebakRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-4-21.
 */
@Component
@Service(path = "/user/\\w+" ,code = "deleteUser", method = MethodType.DELETE, name = "刪除用戶", module = "user" )
public class DeleteUser extends DeleteSampleService<Response> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RolebakRepository rolebakRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    protected Response doService(Map<String, String> prams, String path) throws ServiceException {
        String userId = path.substring(path.lastIndexOf("/") + 1, path.length());
        User user = userRepository.findOne(Long.valueOf(userId));
        profileRepository.deleteByUser(user);
        rolebakRepository.deleteByUsers(user);
        authorityRepository.deleteByUser(user);
        userRepository.delete(user);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
