package com.andx.micro.core.service;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.module.service.ServiceContext;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andongxu on 17-4-7.
 */
public class GenericServiceContext implements ServiceContext {

    private Object serviceInput;
    private Object handlerInput;
    private Map attributes = new HashMap<String, Object>();
    private Service service;

    public GenericServiceContext(Object serviceInput, Service service) {
        this.serviceInput = serviceInput;
    }

    @Override
    public Object getServiceInputObj() {
        return serviceInput;
    }

    @Override
    public <C> C getServiceInputObj(Class<C> c) {
        if (serviceInput.getClass() == c) {
            return (C) serviceInput;
        } else {
            throw new IllegalArgumentException("服务请求参数与目标类型不符");
        }
    }

    @Override
    public Object getHandlerInputObj() {
        return handlerInput;
    }

    @Override
    public <C> C getHandlerInputObj(Class<C> c) {
        if (handlerInput != null && handlerInput.getClass() == c) {
            return (C) handlerInput;
        } else {
            throw new IllegalArgumentException("服务请求参数与目标类型不符");
        }
    }

    @Override
    public void setAttribute(String key, Object value) {
        if (attributes.keySet().contains(key)) {
            throw new KeyAlreadyExistsException("key已经存在");
        } else {
            attributes.put(key, value);
        }
    }

    @Override
    public <C> C getAttribute(String key, Class<C> c) {
        return (C) attributes.get(key);
    }

    @Override
    public Service getService() {
        return service;
    }
}
