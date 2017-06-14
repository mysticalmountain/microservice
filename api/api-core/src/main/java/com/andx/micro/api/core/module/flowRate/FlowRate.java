package com.andx.micro.api.core.module.flowRate;

import com.andx.micro.api.core.Module;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-19.
 */
public interface FlowRate<I extends Request, O extends Response> extends Module<I,O> {

    O flowRate(I i, FlowRateProcessor<Request, Response> processor, Object... args) throws FlowRateException;
}
