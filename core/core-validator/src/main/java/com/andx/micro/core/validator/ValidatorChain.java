package com.andx.micro.core.validator;

import com.andx.micro.api.core.chain.GenericChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;

/**
 * Created by andongxu on 17-1-17.
 */
public class ValidatorChain<I extends Request, O extends Response> extends GenericChain<I, O> {

    private Validator<Request, Response> validator;
    private ValidatorProcessor<Request, Response> validatorProcessor;

    public ValidatorChain(Validator<Request, Response> validator, ValidatorProcessor<Request, Response> validatorProcessor) {
        this.validator = validator;
        this.validatorProcessor = validatorProcessor;
    }

    public O chain(I request, SampleService<I, O> processor, Object... args) {
        long begin = System.currentTimeMillis();
        try {
            Response response = validator.validate(request, validatorProcessor, args);
            if (response.getSuccess() == true) {
                return nextChain.chain(request, processor, args);
            } else {
                return (O) response;
//                throw new ValidatorException(response.getErrorMessage());
            }
        } catch (ValidatorException e) {
            return processor.handlerException(request, e, args);
        }
    }
}
