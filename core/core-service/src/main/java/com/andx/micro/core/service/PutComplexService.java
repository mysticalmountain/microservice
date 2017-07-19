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

/**
 * Created by andongxu on 17-5-5.
 */
@Component
public abstract class PutComplexService<I extends Request, O extends Response> implements ComplexService<I, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    @Transactional
    public O process(I i, Object... args) throws ServiceException {
        try {
            Service service = this.getClass().getAnnotation(Service.class);
            ServiceContext context = new GenericServiceContext(i, service);
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
    public O handlerException(I i, Exception e, Object... args) {
        return doHandleException(i, e, (String) args[1]);
    }

    protected O doHandleException(I i, Exception e, String path) {
        return (O) new Response(e.getMessage(), false);
    }

    protected abstract O doService(I i, String path, ServiceContext context) throws ServiceException;

    @Override
    public void init() {
    }
}
