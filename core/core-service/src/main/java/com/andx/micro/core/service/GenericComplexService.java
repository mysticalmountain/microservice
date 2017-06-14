package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by andongxu on 17-4-7.
 */
public abstract class GenericComplexService<I, O> implements ComplexService<I, O> {

    protected List<ServiceHandler> serviceHandlers = new LinkedList<ServiceHandler>();

    public GenericComplexService() {
        this.init();
    }

    @Override
    public O process(I i, Object... args) throws ServiceException {
        ServiceContext context = new GenericServiceContext(i, this.getClass().getAnnotation(Service.class));
        try {
            return this.doService(i, context, args);
        } catch (Exception e) {
            return handlerException(i, e, args);
        }
    }

    public abstract O doService(I i, ServiceContext context, Object... args) throws ServiceException;

    @Override
    public O handlerException(I i, Exception e, Object... args) {
        return (O) new Response(e.getMessage(), false);
    }

    @Override
    public void init() {
    }

}
