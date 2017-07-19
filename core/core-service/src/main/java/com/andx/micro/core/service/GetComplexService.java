package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.Constant;

import java.util.Map;

/**
 * Created by andongxu on 17-4-13.
 */
public abstract class GetComplexService<O extends Response> implements ComplexService<Map<String, String>, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public O process(Map<String, String> prams, Object... args) throws ServiceException {
        long beginTime = System.currentTimeMillis();
        ServiceContext context = new GenericServiceContext(prams, this.getClass().getAnnotation(Service.class));
        context.setAttribute(Constant.KEY_HTTP_SERVLET_REQUEST, args[0]);
        context.setAttribute(Constant.KEY_PRAMS, prams);
        String executeId = (String) args[args.length - 1];
        try {
            String path = (String) args[1];
            log.info(String.format("[%s -> %s] begin execute prams:%s, path:%s", prams.get(Constant.KEY_REQUEST_ID), executeId, prams, path));
            O o = this.doService(prams, path, context);
            long endTime = System.currentTimeMillis();
            log.info(String.format("[%s -> %s] end execute response:%s, time mills:%d", prams.get(Constant.KEY_REQUEST_ID), executeId, o, (endTime - beginTime)));
            return o;
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            long endTime = System.currentTimeMillis();
            log.error(String.format("[%s -> %s] end execute error:%s, time mills:%d", prams.get(Constant.KEY_REQUEST_ID), executeId, e.getMessage(), (endTime - beginTime)), e);
            return handlerException(prams, e, args);
        }
    }

    @Override
    public O handlerException(Map<String, String> prams, Exception e, Object... args) {
        return doHandleException(prams, e, (String) args[1]);
    }

    @Override
    public void init() {

    }

    protected O doHandleException(Map<String, String> prams, Exception e, String path) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException;

}
