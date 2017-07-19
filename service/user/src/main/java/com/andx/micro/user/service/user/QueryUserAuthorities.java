package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
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
@Service(path = "/user/\\w+/authorities", code = "queryUserAuthorities", name = "查询用户权限", module = "user")
public class QueryUserAuthorities extends GetSampleService<Response> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response doService(Map<String, String> prams, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String userId = tmp.substring(tmp.lastIndexOf("/") + 1);
        User user = userRepository.findOne(Long.valueOf(userId));
        Iterator<Authority> authorities =user.getAuthorities().iterator();
        Set<AuthorityDto> authorityDtos = new HashSet<>();
        while (authorities.hasNext()){
            Authority authority = authorities.next();
            AuthorityDto authorityDto = new AuthorityDto();
            BeanUtils.copyProperties(authority, authorityDto);
            authorityDtos.add(authorityDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(authorityDtos);
        return response;
    }

}
