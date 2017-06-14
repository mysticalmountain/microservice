package com.andx.micro.core.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;

import java.util.Map;

/**
 * Created by andongxu on 17-4-13.
 */
public abstract class GetSampleService<O extends Response> implements SampleService<Map<String,String[]>,O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public O process(Map<String, String[]> prams, Object... args) throws ServiceException {
        try {
            String path = (String) args[1];
            return doService(prams, path);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(prams, e, args);
        }
    }

    @Override
    public O handlerException(Map<String, String[]> prams, Exception e, Object... args) {
        return doHandleException(prams, e, (String) args[1]);
    }

    protected O doHandleException(Map<String, String[]> prams, Exception e, String path) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(Map<String, String[]> prams, String path) throws ServiceException;
}
