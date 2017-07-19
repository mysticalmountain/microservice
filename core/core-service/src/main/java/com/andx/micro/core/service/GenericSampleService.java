package com.andx.micro.core.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;

/**
 * Created by andongxu on 17-4-14.
 */
public abstract class GenericSampleService<I, O> implements SampleService<I, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public O process(I i, Object ... args) throws ServiceException {
        try {
            log.info(String.format("execute prams %s", i));
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
