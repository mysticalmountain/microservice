package com.andx.micro.permission.service.web;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.permission.BaseTest;
import com.andx.micro.permission.dto.role.RoleDto;
import org.bouncycastle.cert.ocsp.Req;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 17-6-16.
 */
public class RoleServiceTest extends BaseTest {

//    @Autowired
//    private RestTemplate restTemplate;


    @Test
    public void saveRole() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set(HttpHeaders.ACCEPT_ENCODING, "utf-8");
        httpHeaders.set(HttpHeaders.CONTENT_ENCODING, "utf-8");
        httpHeaders.add(HttpHeaders.CONNECTION, "Keep-Alive");
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Request<RoleDto> request = new Request<RoleDto>();
        RoleDto roleDto = new RoleDto();
        roleDto.setName("管理员");
        Set<String> permissionIds = new HashSet<String>();
        permissionIds.add("10000207");
        roleDto.setPermissionIds(permissionIds);
        request.setData(roleDto);
        HttpEntity<Request> entity = new HttpEntity<Request>(request, httpHeaders);
        restTemplate.exchange("http://127.0.0.1:8082/service/roles", HttpMethod.POST, entity, Response.class);
//        Response response = restTemplate.postForObject("http://127.0.0.1:8082/service/roles", roleDto, Response.class);


    }
}
