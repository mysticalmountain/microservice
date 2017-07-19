package com.andx.micro.user.service.user.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
public class GetUserOrgHandler extends GenericServiceHandler<Long, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long doHandle(Long aLong, ServiceContext context) throws HandlerException {
        User user = userRepository.findOne(aLong);
        Long orgId = user.getOrg().getId();
        return orgId;
    }

    @Override
    public Boolean support(Long aLong, ServiceContext context) {
        return true;
    }
}
