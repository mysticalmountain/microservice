package com.andx.micro.user.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.OrgRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
@Service(path = "/org/\\w+/users", code = "queryOrgUsers", method = MethodType.GET, name = "查询某机构下用户", module = "user")
public class QueryOrgUsers extends GetSampleService<Response<List<UserDto>>> {

    @Autowired
    private OrgRepository orgRepository;

    private static final Pattern pattern = Pattern.compile("\\w+/");

    @Override
    public Response<List<UserDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String orgId = this.getOrgId(path);
        Org org = orgRepository.findOne(Long.valueOf(orgId));
        if (org == null) {
            throw new ServiceException(String.format("机构[%s]不存在", orgId));
        }
        List<UserDto> userDtos = new ArrayList<>();
        fetch(userDtos, org);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(userDtos);
        return response;
    }

    private void fetch(List<UserDto> userDtos, Org org) {
        List<User> users = org.getUsers();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }
        List<Org> sons = org.getSons();
        for (Org sonOrg : sons) {
            fetch(userDtos, sonOrg);
        }
    }

    private String getOrgId(String path) {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        return tmp.substring(tmp.lastIndexOf("/") + 1);
    }
}
