package com.andx.micro.user.dto;

import com.andx.micro.user.model.enums.UserType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by andongxu on 17-2-16.
 */
public class UserDto implements Serializable{

    private Long id;

    @NotNull(message = "不允许为空")
    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,32}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,5}")
    private String name;

    @NotNull(message = "不允许为空")
    private String username;

    @NotNull(message = "不允许为空")
    private UserType userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    //    @Valid
//    private ProfileDto profileDto;

//    @Valid
//    private AuthorityDto authorityDto;

//    @Valid
//    private Set<RolebakDto> roleDtos;

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
