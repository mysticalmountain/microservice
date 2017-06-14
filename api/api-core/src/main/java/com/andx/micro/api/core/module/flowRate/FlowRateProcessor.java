package com.andx.micro.api.core.module.flowRate;

import com.andx.micro.api.core.Processor;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-19.
 */
public interface FlowRateProcessor<I extends Request, O extends Response> extends Processor<I, O> {

    Response flowRate(Request request, Object... args);

    Response concurrent(Request request, Object... args);


}
