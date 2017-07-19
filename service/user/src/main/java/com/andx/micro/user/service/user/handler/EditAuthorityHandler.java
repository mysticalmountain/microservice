package com.andx.micro.user.service.user.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.dto.AuthorityDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.User;
import com.andx.micro.user.model.enums.AuthType;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-20.
 */
@Component
public class EditAuthorityHandler extends GenericServiceHandler<AuthorityDto, AuthorityDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public AuthorityDto doHandle(AuthorityDto authorityDto, ServiceContext context) throws HandlerException {
        Long userId = context.getAttribute("userId", Long.class);
        User user = userRepository.findOne(userId);
        Authority authority = null;
        if (authorityDto.getId() != null) {
            authority = authorityRepository.findOne(Long.valueOf(authorityDto.getId()));
            if (authority == null) {
                throw new HandlerException(String.format("账户不存在ID[%s]", authorityDto.getId()));
            }
        } else {
            authority = new Authority();
            authority.setAuthType(AuthType.USERNAME);
        }
        BeanUtils.copyProperties(authorityDto, authority);
        authority.setUser(user);
        authority = authorityRepository.save(authority);
        AuthorityDto res = new AuthorityDto();
        BeanUtils.copyProperties(authority, res);
        return res;
    }

    @Override
    public Boolean support(AuthorityDto authorityDto, ServiceContext context) {
        return true;
    }
}
