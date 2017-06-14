package com.andx.micro.api.core.module.service;

import com.andx.micro.api.core.Processor;

/**
 * Created by andongxu on 17-3-31.
 */
public interface ComplexService<I, O> extends Processor<I, O> {

    O process(I i, Object... args) throws ServiceException;

    O handlerException(I i, Exception e, Object... args);

    void init();
}
