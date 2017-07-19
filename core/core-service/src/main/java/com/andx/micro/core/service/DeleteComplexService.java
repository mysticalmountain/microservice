package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.Constant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@Component
public abstract class DeleteComplexService<O extends Response> implements ComplexService<Map<String,String>, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    @Transactional
    public O process(Map<String,String> i, Object... args) throws ServiceException {
        try {
            ServiceContext context = new GenericServiceContext(i, this.getClass().getAnnotation(Service.class));
            context.setAttribute(Constant.KEY_HTTP_SERVLET_REQUEST, args[0]);
            context.setAttribute(Constant.KEY_PRAMS, i);
            String path = (String) args[1];
            return doService(i, path, context);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(i, e, args);
        }
    }

    @Override
    public O handlerException(Map<String,String> i, Exception e, Object... args) {
        return (O) new Response(e.getMessage(), false);
    }

    @Override
    public void init() {

    }

    protected abstract O doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException;
}
