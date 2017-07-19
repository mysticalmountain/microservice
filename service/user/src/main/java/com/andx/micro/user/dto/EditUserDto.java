package com.andx.micro.user.dto;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by andongxu on 17-2-16.
 */
public class EditUserDto implements Serializable{

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

    //    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//    }

//    public String getUsername() {
//        return username;
//    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

//    public UserType getUserType() {
//        return userType;
//    }

//    public void setUserType(UserType userType) {
//        this.userType = userType;
//    }

//    public ProfileDto getProfileDto() {
//        return profileDto;
//    }

//    public void setProfileDto(ProfileDto profileDto) {
//        this.profileDto = profileDto;
//    }

//    public AuthorityDto getAuthorityDto() {
//        return authorityDto;
//    }

//    public void setAuthorityDto(AuthorityDto authorityDto) {
//        this.authorityDto = authorityDto;
//    }

//    public Set<RolebakDto> getRoleDtos() {
//        return roleDtos;
//    }

//    public void setRoleDtos(Set<RolebakDto> roleDtos) {
//        this.roleDtos = roleDtos;
//    }
}
