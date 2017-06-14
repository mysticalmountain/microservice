package com.andx.micro.core.validator;

import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-4-30.
 */
@Component
public class PermissionValidator implements Validator<PermissionValidatorDto, Boolean> {
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
        return validatorProcessor.process(permissionValidatorDto, args);
    }
}
