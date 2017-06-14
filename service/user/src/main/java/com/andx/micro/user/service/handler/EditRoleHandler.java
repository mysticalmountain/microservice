package com.andx.micro.user.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.RolebakRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-4-20.
 */
@Component
public class EditRoleHandler extends GenericServiceHandler<Set<RolebakDto>, Boolean> {

    @Autowired
    private RolebakRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean doHandle(Set<RolebakDto> rolebaks, ServiceContext context) throws HandlerException {
        Long userId = context.getAttribute("userId", Long.class);
        User user = userRepository.findOne(userId);
        roleRepository.deleteByUsers(user);
        Iterator<RolebakDto> userRoleDtoIterable = rolebaks.iterator();
        Set<Rolebak> rolebakSet = new HashSet<Rolebak>();
        while (userRoleDtoIterable.hasNext()) {
            RolebakDto rolebakDto = userRoleDtoIterable.next();
            Rolebak rolebak = roleRepository.findOne(rolebakDto.getRoleId());
            if (rolebak == null) {
                rolebak = new Rolebak();
                BeanUtils.copyProperties(rolebakDto, rolebak);
                roleRepository.save(rolebak);
            }
            rolebakSet.add(rolebak);
        }
        user.setRoles(rolebakSet);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean support(Set<RolebakDto> rolebaks, ServiceContext context) {
        return true;
    }
}
