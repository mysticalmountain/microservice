package com.andx.micro.api.core.module.service;

import com.andx.micro.api.core.Module;
import com.andx.micro.api.core.Processor;

/**
 * Created by andongxu on 16-12-29.
 */
public interface Service<I, O> extends Module<I, O> {

    public O service(I i, Processor<I, O> processor, Object... args) throws ServiceException;
}
