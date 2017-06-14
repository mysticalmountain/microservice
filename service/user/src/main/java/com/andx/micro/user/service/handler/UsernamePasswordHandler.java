package com.andx.micro.user.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.support.web.service.login.LoginDto;
import com.andx.micro.user.model.Authority;
import com.andx.micro.user.repository.AuthorityRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-13.
 */
@Component
public class UsernamePasswordHandler extends GenericServiceHandler<LoginDto, String> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public String doHandle(LoginDto loginDto, ServiceContext context) throws HandlerException {
        Authority authority = authorityRepository.findByAccountNo(loginDto.getUsername());
        if (authority == null) {
            throw new HandlerException("用户不存在");
        }
        if (!authority.getPassword().equals(loginDto.getPassword())) {
            throw new HandlerException("密码错误");
        }
        return String.valueOf(authority.getUser().getId());
    }

    @Override
    public Boolean support(LoginDto loginDto, ServiceContext context) {
        return true;
    }
}
