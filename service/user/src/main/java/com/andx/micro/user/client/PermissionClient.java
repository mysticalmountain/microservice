package com.andx.micro.user.client;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.user.config.ServiceDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by andongxu on 17-5-26.
 */
@FeignClient(value = "permission")
public interface PermissionClient {

    @RequestMapping(method = RequestMethod.GET, value = "/service/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response getPermissions(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/owner/{ownerId}/resource/{resourceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response queryOwnerResource(@PathVariable("ownerId") String ownerId, @PathVariable("resourceId") String resourceId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "service/service/{serviceCode}/noPermission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response getServiceNoPermission(@PathVariable("serviceCode") String serviceCode, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "service/service/init", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response initSevice(Request<ServiceDto> request);


}
