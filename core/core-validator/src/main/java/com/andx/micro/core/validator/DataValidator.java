package com.andx.micro.core.validator;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-1-6.
 */
@Component
public class DataValidator implements Validator<Request,Response> {

    private Log log = Log4jLogFactory.getLogFactory().getLog(DataValidator.class);

    public void init() throws Throwable {

    }

    public void destory() throws Throwable {

    }

    public void notify(Request request) {

    }

    public Response validate(Request request, ValidatorProcessor<Request, Response> validatorProcessor, Object... args) throws ValidatorException {
        long begin = System.currentTimeMillis();
        try {
            return validatorProcessor.process(request, args);
        } finally {
            long end = System.currentTimeMillis();
            log.info("execute module [validator] time mills:" + (end - begin) + " ms");
        }
    }
}
