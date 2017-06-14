package com.andx.micro.core.service;

import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.handler.HandlerException;
import com.andx.micro.api.core.module.service.handler.ServiceHandler;

/**
 * Created by andongxu on 17-4-13.
 */
public abstract class GenericServiceHandler<I, O> implements ServiceHandler<I, O> {



    @Override
    public O handle(I i, ServiceContext context) throws HandlerException {
        if (support(i, context)) {
            try {
                return doHandle(i, context);
            } catch (Exception e) {
                return catcheException(e);
            }
        }
        return null;
    }

    @Override
    public O catcheException(Exception e) throws HandlerException {
        if (e instanceof HandlerException) {
            throw (HandlerException)e;
        }
        throw new HandlerException(e.getMessage(), e);
    }

    public abstract O doHandle(I i, ServiceContext context) throws HandlerException;

    public abstract Boolean support(I i, ServiceContext context);

}
