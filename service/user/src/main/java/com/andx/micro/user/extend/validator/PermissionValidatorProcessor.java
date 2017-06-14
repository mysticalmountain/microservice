package com.andx.micro.user.extend.validator;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.core.validator.PermissionValidatorDto;
import com.andx.micro.user.client.PermissionClient;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import com.andx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<PermissionValidatorDto, Boolean> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private PermissionClient permissionClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean process(PermissionValidatorDto permissionValidatorDto, Object... args) throws ValidatorException {
//        URI uri = loadBalancer.choose("permission").getUri();
//        Response<Boolean> noPermission = restTemplate.getForObject(String.format("http://%s:%d/service/services/%s/noPermission", "permission", uri.getPort(), permissionValidatorDto.getServiceCode()), Response.class);
        Response<Boolean> noPermission = permissionClient.getServices_NoPermission(permissionValidatorDto.getServiceCode());
//        Response<Boolean> noPermission = restTemplate.getForObject(String.format("http://www.micro.com/permission/service/services/%s/noPermission", permissionValidatorDto.getServiceCode()), Response.class);
//        if (noPermission.getSuccess() == true && noPermission.getData() == true) {
        if (true) {
            return true;
        }
        User user = userRepository.findOne(Long.valueOf(permissionValidatorDto.getOwnerId()));
        Set<Rolebak> roles = user.getRoles();
        Iterator<Rolebak> rolebakIterator = roles.iterator();
        while (rolebakIterator.hasNext()) {
            Rolebak rolebak = rolebakIterator.next();
            Response response = restTemplate.getForObject("http://www.micro.com/permission/service/owners/" + rolebak.getRoleId() + "/resources/" + permissionValidatorDto.getServiceCode(), Response.class);
            if (response.getSuccess() == true) {
                return true;
            }
        }
        return false;
    }
}
