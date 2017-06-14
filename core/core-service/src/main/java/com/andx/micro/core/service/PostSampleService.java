package com.andx.micro.core.service;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andongxu on 17-4-19.
 */
public abstract class PostSampleService<I extends Request, O extends Response> implements SampleService<I, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    @Transactional
    public O process(I i, Object... args) throws ServiceException {
        try {
            return doService(i);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(i, e, args);
        }
    }

    @Override
    public O handlerException(I i, Exception e, Object... args) {
        return doHandleException(i, e);
    }

    protected O doHandleException(I i, Exception e) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(I i) throws ServiceException;

}
