package com.andx.micro.management.dto;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by andongxu on 17-2-16.
 */
public class EditUserDto implements Serializable {

    @Valid
    private UserDto userDto;

    @Valid
    private ProfileDto profileDto;

    @Valid
    private AuthorityDto authorityDto;

    @Valid
    private Set<RolebakDto> roleDtos;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }

    public AuthorityDto getAuthorityDto() {
        return authorityDto;
    }

    public void setAuthorityDto(AuthorityDto authorityDto) {
        this.authorityDto = authorityDto;
    }

    public Set<RolebakDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(Set<RolebakDto> roleDtos) {
        this.roleDtos = roleDtos;
    }
}

