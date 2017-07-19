package com.andx.micro.core.validator;

import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-30.
 */
@Component
public class PermissionValidator implements Validator<PermissionValidatorDto, Boolean> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public void init() throws Throwable {

    }

    @Override
    public void destory() throws Throwable {

    }

    @Override
    public void notify(PermissionValidatorDto permissionValidatorDto) {

    }

    @Override
    public Boolean validate(PermissionValidatorDto permissionValidatorDto, ValidatorProcessor<PermissionValidatorDto, Boolean> validatorProcessor, Object... args) throws ValidatorException {
        long beginTime = System.currentTimeMillis();
        log.info(String.format("[%s -> %s] begin execute prams:%s", permissionValidatorDto.getRequestId(), permissionValidatorDto.getExecuteId(), permissionValidatorDto));
        Boolean result = validatorProcessor.process(permissionValidatorDto, args);
        long endTime = System.currentTimeMillis();
        log.info(String.format("[%s -> %s] end execute response:%b, time mills:%d", permissionValidatorDto.getRequestId(), permissionValidatorDto.getExecuteId(), result, (endTime - beginTime)));
        return result;
    }
}
