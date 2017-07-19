package com.andx.micro.permission.extend.validator;

import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.validator.PermissionValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andongxu on 17-2-21.
 */
@Component
public class PermissionValidatorProcessor implements ValidatorProcessor<PermissionValidatorDto, Boolean> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());
    @Autowired
    @Qualifier("channelValidateHandler")
    private ServiceHandler<PermissionValidatorDto, Boolean> channelValidateHandler;
    @Autowired
    @Qualifier("servicePermissionExistsHandler")
    private ServiceHandler<String, Boolean> serviceValidateHandler;

    @Override
    public Boolean process(PermissionValidatorDto dto, Object... args) throws ValidatorException {
        try {
            String serviceCode = dto.getServiceCode();
            Boolean hasPermission = false;
            hasPermission = serviceValidateHandler.handle(serviceCode, null);
            if (hasPermission == false) {       //服务无权限控制
                return true;
            }
            hasPermission = false;
            //验证渠道是否有权访问服务
            Boolean permissionExists = serviceValidateHandler.handle(serviceCode, null);
            if (permissionExists) {
                hasPermission = channelValidateHandler.handle(dto, null);
                return hasPermission == null ? false : hasPermission;
            } else {
                return true;
            }
        } catch (HandlerException e) {
            throw new ValidatorException(e.getMessage(), e);
        }
    }
}
