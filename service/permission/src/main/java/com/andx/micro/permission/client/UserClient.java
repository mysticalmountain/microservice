package com.andx.micro.permission.client;

import com.andx.micro.api.core.dto.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by andongxu on 17-7-7.
 */
@FeignClient(value = "user")
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/service/roles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response newRole();

    @RequestMapping(method = RequestMethod.GET, value = "/service/list-orgs/{orgId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<OrgDto>> getListOrgs(@PathVariable("orgId") Long orgId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);
}
