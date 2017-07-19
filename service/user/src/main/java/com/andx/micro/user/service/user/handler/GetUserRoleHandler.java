package com.andx.micro.user.service.user.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.RolebakRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andongxu on 17-7-5.
 */
@Component
public class GetUserRoleHandler extends GenericServiceHandler<Long, List<Long>> {

    @Autowired
    private RolebakRepository rolebakRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Long> doHandle(Long aLong, ServiceContext context) throws HandlerException {
        User user = userRepository.findOne(aLong);
        List<Long> roleIds = new ArrayList<>();
        Iterator<Rolebak> rolebakIterator = user.getRoles().iterator();
        while (rolebakIterator.hasNext()) {
            Rolebak rolebak = rolebakIterator.next();
            roleIds.add(rolebak.getRoleId());
        }
        return roleIds;
    }

    @Override
    public Boolean support(Long aLong, ServiceContext context) {
        return true;
    }
}
