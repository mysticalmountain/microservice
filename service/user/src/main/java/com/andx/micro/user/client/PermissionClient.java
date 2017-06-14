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
@FeignClient(value = "permission", fallback = PermissionClientFallbak.class)
public interface PermissionClient {

    @RequestMapping(method = RequestMethod.GET, value = "/service/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response getPermissions();

    @RequestMapping(method = RequestMethod.GET, value = "service/services/{serviceCode}/noPermission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response getServices_NoPermission(@PathVariable("serviceCode") String serviceCode);

    @RequestMapping(method = RequestMethod.GET, value = "service/services/init", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response initSevice(Request<ServiceDto> request);


}
