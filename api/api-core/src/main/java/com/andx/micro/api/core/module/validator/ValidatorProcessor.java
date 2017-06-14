package com.andx.micro.api.core.module.validator;

import com.andx.micro.api.core.Processor;

/**
 * Created by andongxu on 17-1-6.
 */
public interface ValidatorProcessor<I, O> extends Processor<I, O> {

    O process(I i, Object... args) throws ValidatorException;
}
