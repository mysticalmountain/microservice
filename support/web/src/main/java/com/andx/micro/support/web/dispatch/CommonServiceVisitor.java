package com.andx.micro.support.web.dispatch;

import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/11/16.
 */
@Component
public class CommonServiceVisitor<D, O> implements ServiceVisitor<D, O> {

//    @Autowired(required = false)
//    private ExecuteTemplate<D, ServiceProcessor, O> execute;

//    public O visit(ServiceProcessor<D, O> service, ServiceData<D> serviceData) throws DispatchException {
//        D d = serviceData.get();
//        return execute.execute(d, service);
//    }
}
