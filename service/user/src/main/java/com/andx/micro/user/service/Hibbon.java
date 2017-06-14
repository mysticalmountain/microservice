package com.andx.micro.user.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.client.PermissionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * Created by andongxu on 17-5-26.
 */
@Component
@Service(path = "/hibbon", code = "hibbon",method = MethodType.GET, name = "测试", module = "用户")
public class Hibbon extends GetSampleService<Response> {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Map<String, String[]> prams, String path) throws ServiceException {
        // loadBanlancer
//        URI uri = loadBalancer.choose("permission").getUri();
//        String str = "http://" + uri.getHost() + ":" + uri.getPort() + "/service/permissions";
//        System.out.println(str);
//        Response response =  restTemplate.getForObject(str, Response.class);

        Response response = permissionClient.getPermissions();
        return response;

    }

    private void callbak() {

    }

}
