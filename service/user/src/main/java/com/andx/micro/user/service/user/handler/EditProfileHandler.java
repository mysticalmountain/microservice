package com.andx.micro.user.service.user.handler;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.core.service.GenericServiceHandler;
import com.andx.micro.user.dto.ProfileDto;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.ProfileRepository;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-20.
 */
@Component
public class EditProfileHandler extends GenericServiceHandler<ProfileDto, ProfileDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileDto doHandle(ProfileDto profileDto, ServiceContext context) throws HandlerException {
        Long userId = context.getAttribute("userId", Long.class);
        User user = userRepository.findOne(userId);
        Profile profile = null;
        if (profileDto.getId() != null) {
            profile = profileRepository.findOne(Long.valueOf(profileDto.getId()));
            if (profile == null) {
                throw new HandlerException(String.format("摘要不存在ID[%s]", profileDto.getId()));
            }
        } else {
            profile = new Profile();
        }
        BeanUtils.copyProperties(profileDto, profile);
        profile.setUser(user);
        profile = profileRepository.save(profile);
        ProfileDto res = new ProfileDto();
        BeanUtils.copyProperties(profile, res);
        return res;
    }

    @Override
    public Boolean support(ProfileDto profileDto, ServiceContext context) {
        return true;
    }
}
