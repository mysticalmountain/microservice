package com.andx.micro.api.core.module.service.handler;

import com.andx.micro.api.core.module.service.ServiceContext;

/**
 * Created by andongxu on 17-3-31.
 */
public interface ServiceHandler<I, O> {

    O handle(I i, ServiceContext context) throws HandlerException;

    O catcheException(Exception e) throws HandlerException;

}
