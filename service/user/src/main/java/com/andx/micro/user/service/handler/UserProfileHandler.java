package com.andx.micro.user.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andongxu on 17-5-9.
 */
@Component
public class UserProfileHandler extends GenericServiceHandler<Long, List<Long>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Long> doHandle(Long aLong, ServiceContext context) throws HandlerException {
        User user = userRepository.findOne(aLong);
        Iterator<Rolebak> rolebaks = user.getRoles().iterator();
        List<Long> roleIds = new ArrayList<>();
        while (rolebaks.hasNext()) {
            Rolebak rolebak = rolebaks.next();
            roleIds.add(rolebak.getRoleId());
        }
        return roleIds;
    }

    @Override
    public Boolean support(Long aLong, ServiceContext context) {
        return true;
    }
}
