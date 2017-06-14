package com.andx.micro.core.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;

/**
 * Created by andongxu on 17-4-14.
 */
public abstract class GenericSampleService<I, O> implements SampleService<I, O> {

    @Override
    public O process(I i, Object ... args) throws ServiceException {
        try {
            return doService(i, args);
        } catch (Exception e) {
            return handlerException(i, e, args);
        }
    }

    public abstract O doService(I i, Object ... args) throws ServiceException;

    @Override
    public O handlerException(I i, Exception e, Object ... args) {
        return (O) new Response(e.getMessage(), false);
    }

}
