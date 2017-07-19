package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-7-3.
 */
@Component
@Service(path = "/user/\\w+/orgs", code = "queryUsersOrgs", name = "查询用户所属机构", module = "user")
public class QueryUserOrgs extends GetSampleService<Response<OrgDto>> {

    @Autowired
    private UserRepository userRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    protected Response<OrgDto> doService(Map<String, String> prams, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String userId = tmp.substring(tmp.lastIndexOf("/") + 1);
        User user = userRepository.findOne(Long.valueOf(userId));
        Org org = user.getOrg();
        OrgDto orgDto = new OrgDto();
        if (org != null) {
            orgDto.setId(org.getId());
            orgDto.setName(org.getName());
            orgDto.setOd(org.getOd());
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(orgDto);
        return response;
    }
}
