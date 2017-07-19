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
    private PermissionClient permissionClient;

    @Override
    public Boolean process(PermissionValidatorDto permissionValidatorDto, Object... args) throws ValidatorException {
        Response<Boolean> noPermission = permissionClient.getServiceNoPermission(permissionValidatorDto.getServiceCode(), permissionValidatorDto.getOwnerId(), permissionValidatorDto.getRequestId());
        if (noPermission.getSuccess() == true) {
            if (noPermission.getData() == true) {
                return true;
            }
        }

        Response res = permissionClient.queryOwnerResource(permissionValidatorDto.getOwnerId(), permissionValidatorDto.getServiceCode(), permissionValidatorDto.getOwnerId(), permissionValidatorDto.getRequestId());
        if (res.getSuccess() == true) {
            return true;
        } else {
            throw new ValidatorException(String.format("渠道 [%s] 无权访问服务 [%s]", permissionValidatorDto.getOwnerId(), permissionValidatorDto.getServiceCode()));
        }
    }
}
