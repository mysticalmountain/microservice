package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andongxu on 17-4-20.
 */
public abstract class PostComplexService<I, O> implements ComplexService<I, O> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    protected List<ServiceHandler> serviceHandlers = new LinkedList<ServiceHandler>();

    private static String METHOD_SERVICE = "doService";
    private static String METHOD_COMMIT = "doCommit";
    private static String METHOD_AUDIT = "doAudit";

    public PostComplexService() {
        this.init();
    }

    @Override
    @Transactional
    public O process(I i, Object... args) throws ServiceException {
        ServiceContext context = new GenericServiceContext(i, this.getClass().getAnnotation(Service.class));
        context.setAttribute("httpServletRequest", args[0]);
        try {
//            Method targetMethod = this.getClass().getMethod(String.valueOf(args[args.length - 1]), ServiceContext.class);
//            return (O) targetMethod.invoke(i, context);
            return this.doService(i, context);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return handlerException(i, e, args);
        }
    }

    protected abstract O doService(I i, ServiceContext context) throws ServiceException;

    protected O doCommit(I i, ServiceContext context) throws ServiceException {
        throw new ServiceException("提交逻辑未实现");
    }

    protected O doAudit(I i, ServiceContext context) throws ServiceException {
        throw new ServiceException("审核逻辑未实现");
    }

    @Override
    public O handlerException(I i, Exception e, Object... args) {
        log.error(e.getMessage());
        return doHandleException(i, e);
    }

    protected O doHandleException(I i, Exception e) {
        return (O) new Response(e.getMessage(), false);
    }

    @Override
    public void init() {
    }
}
