package com.andx.micro.core.service;

import com.andx.micro.api.core.chain.GenericChain;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.ServiceException;

/**
 * Created by andongxu on 17-1-4.
 */
public class ServiceChain extends GenericChain<Request, Response> {

    private Service<Request, Response> service;

    public ServiceChain(Service<Request, Response> service) {
        this.service = service;
    }

    public Response chain(Request request, SampleService<Request, Response> processor, Object... args) {
        try {
            Response response = service.service(request, processor, args);
            if (response.getSuccess() == true) {
                if (nextChain == null) {
                    return response;
                } else {
                    return nextChain.chain(request, processor, args);
                }
            }
            throw new ServiceException("");
        } catch (ServiceException e) {
            return processor.handlerException(request, e, args);
        }
    }
}
