package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;

import java.util.Map;

/**
 * Created by andongxu on 17-4-13.
 */
public abstract class GetComplexService<O extends Response> implements ComplexService<Map<String, String[]>, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public O process(Map<String, String[]> i, Object... args) throws ServiceException {
        ServiceContext context = new GenericServiceContext(i, this.getClass().getAnnotation(Service.class));
        context.setAttribute("httpServletRequest", args[0]);
        try {
            String path = (String) args[1];
            return this.doService(i, path, context);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(i, e, args);
        }
    }

    @Override
    public O handlerException(Map<String, String[]> i, Exception e, Object... args) {
        return doHandleException(i, e, (String) args[1]);
    }

    @Override
    public void init() {

    }

    protected O doHandleException(Map<String, String[]> prams, Exception e, String path) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(Map<String, String[]> prams, String path, ServiceContext context) throws ServiceException;

}
