package com.andx.micro.user.service.user.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.dto.UserDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.OrgRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-12.
 */
@Component
public class EditUserHandler extends GenericServiceHandler<UserDto, UserDto> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrgRepository orgRepository;

    @Override
    public UserDto doHandle(UserDto userDto, ServiceContext context) throws HandlerException {
        User user = null;
        if (userDto.getId() != null) {
            user = userRepository.findOne(userDto.getId());
            if (user == null) {
                throw new HandlerException(String.format("用户不存在ID【%s】" + userDto.getId()));
            }
        } else {
            user = new User();
        }

        Org org = orgRepository.findOne(Long.valueOf(userDto.getOrgId()));
        if (org == null) {
            throw new HandlerException(String.format("机构[%s]不存在", userDto.getOrgId()));
        }
        BeanUtils.copyProperties(userDto, user);
        user.setOrg(org);
        user = userRepository.save(user);
        UserDto res = new UserDto();
        BeanUtils.copyProperties(user, res);
        return res;
    }

    @Override
    public Boolean support(UserDto userDto, ServiceContext context) {
        return true;
    }
}
