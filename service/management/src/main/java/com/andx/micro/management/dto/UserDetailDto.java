package com.andx.micro.management.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by andongxu on 17-2-24.
 */
public class UserDetailDto implements Serializable {

    private Long id;

    private String name;

    private String username;

    private Set<AuthorityDto> authorityDtos;

    private ProfileDto profileDto;

    private Set<RolebakDto> rolebakDtos;

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

    public Set<AuthorityDto> getAuthorityDtos() {
        return authorityDtos;
    }

    public void setAuthorityDtos(Set<AuthorityDto> authorityDtos) {
        this.authorityDtos = authorityDtos;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }

    public Set<RolebakDto> getRolebakDtos() {
        return rolebakDtos;
    }

    public void setRolebakDtos(Set<RolebakDto> rolebakDtos) {
        this.rolebakDtos = rolebakDtos;
    }
}
