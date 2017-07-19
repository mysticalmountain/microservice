package com.andx.micro.management.client;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.management.dto.*;
import com.andx.micro.management.dto.OrgDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@FeignClient(value = "user")
public interface UserClient {

    //user
    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<UserDto> queryUser(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}/profile", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<ProfileDto> queryUserProfile(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}/authorities", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<AuthorityDto>> queryUserAuthorities(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}/roles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<RolebakDto>> queryUserRoles(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/org/{orgId}/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<UserDto>> queryOrgUsers(@PathVariable("orgId") Long orgId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);


    @RequestMapping(method = RequestMethod.DELETE, value = "/service/user/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response deleteUser(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "/service/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<EditUserDto> newUser(Request<EditUserDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/user/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response editUser(@PathVariable("id") Long id, Request<EditUserDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/user/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response editUser1(@PathVariable("id") Long id, Request<EditUserDto> request);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/username/{username}/password/{password}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<Long> queryUserUsernamePassword(@PathVariable("username") String username, @PathVariable("password") String password, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);


    //org
    @RequestMapping(method = RequestMethod.PUT, value = "/service/org/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<OrgDto> editOrg(@PathVariable("id") Long id, Request<OrgDto> request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/service/org/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response deleteOrg(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "/service/org", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<OrgDto> newOrg(Request<OrgDto> request);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}/orgs", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<OrgDto>> queryUserOrgs(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/user/{userId}/org", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<OrgDto> queryUserOrg(@PathVariable("userId") Long userId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/list-orgs/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<OrgDto>> queryListOrgs(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/org/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<OrgDto> queryOrg(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

}
