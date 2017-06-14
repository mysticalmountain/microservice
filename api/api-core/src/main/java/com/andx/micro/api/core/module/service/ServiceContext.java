package com.andx.micro.api.core.module.service;

import com.andx.micro.api.core.*;

/**
 * Created by andongxu on 17-4-7.
 */
public interface ServiceContext {

    Object getServiceInputObj();

    <C> C getServiceInputObj(Class<C> c);

    Object getHandlerInputObj();

    <C> C getHandlerInputObj(Class<C> c);

    void setAttribute(String key, Object value);

    <C> C getAttribute(String key, Class<C> c);

    com.andx.micro.api.core.Service getService();

}
