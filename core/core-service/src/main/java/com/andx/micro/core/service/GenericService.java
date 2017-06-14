package com.andx.micro.core.service;


import com.andx.micro.api.core.Processor;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.Service;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;

/**
 * Created by andongxu on 16-12-29.
 */
public class GenericService implements Service<Request, Response> {

    private Log log = Log4jLogFactory.getLogFactory().getLog(GenericService.class);

    public void init() throws Throwable {

    }

    public void destory() throws Throwable {

    }

    public void notify(Request request) {

    }

    public Response service(Request request, Processor<Request, Response> processor, Object... args) throws ServiceException {
        long begin = System.currentTimeMillis();
        try {
            if (processor instanceof SampleService) {
                return ((SampleService<Request, Response>) processor).process(request, args);
            }
            return null;
        } finally {
            long end = System.currentTimeMillis();
            log.info("execute module [service] time mills:" + (end - begin) + " ms");
        }
    }

}
