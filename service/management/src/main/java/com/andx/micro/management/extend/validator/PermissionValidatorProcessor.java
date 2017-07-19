package com.andx.micro.management.extend.validator;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.core.validator.PermissionValidatorDto;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.client.UserClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<PermissionValidatorDto, Boolean> {

    @Autowired
    private UserClient userClient;
    @Autowired
    private PermissionClient permissionClient;

    @Override
    public Boolean process(PermissionValidatorDto permissionValidatorDto, Object... args) throws ValidatorException {
        Response<List<ServiceDto>> res = permissionClient.queryServicesNoPermission(Constant.MODULE, permissionValidatorDto.getRequestId());
        List<ServiceDto> serviceDtos = res.getData();
        //判断服务是否有权限控制
        boolean exist = false;
        for (ServiceDto serviceDto : serviceDtos) {
            if (serviceDto.getCode().equals(permissionValidatorDto.getServiceCode())) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return true;
        }
        //判断角色是否有访问服务的权限
        HttpServletRequest request = (HttpServletRequest) args[0];
        List<Long> roleIds = (List<Long>) request.getSession().getAttribute("roleIds");
        exist = false;
        Response response;
        for (Long roleId : roleIds) {
            if (roleId.intValue() == 10000682) {    //TODO 系统管理员
                return true;
            }
            response = permissionClient.queryOwnerResource(String.valueOf(roleId), permissionValidatorDto.getServiceCode(), Constant.MODULE, permissionValidatorDto.getRequestId());
            exist = response.getSuccess();
            if (exist) {
                break;
            }
        }
        if (exist == false) {
            throw new ValidatorException(String.format("角色 [%s] 无权限访问服务 [%s]", roleIds.toString(), permissionValidatorDto.getServiceCode()));
        }
        return exist;
    }
}
