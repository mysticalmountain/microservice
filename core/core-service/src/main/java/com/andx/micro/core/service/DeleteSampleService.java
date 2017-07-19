package com.andx.micro.core.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLog;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.Map;

/**
 * Created by andongxu on 17-4-13.
 */
public abstract class DeleteSampleService<O extends Response> implements SampleService<Map<String, String>, O> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    @Transactional
    public O process(Map<String, String> n, Object... args) throws ServiceException {
        try {
            String path = (String) args[1];
            return doService(n, path);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(n, e, args);
        }
    }

    @Override
    public O handlerException(Map<String, String> n, Exception e, Object... args) {
        return doHandleException(e, (String) args[1]);
    }

    protected O doHandleException(Exception e, String path) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(Map<String, String> prams, String path) throws ServiceException;
}
