package com.andx.micro.api.core.module.idempotent;

import com.andx.micro.api.core.Processor;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-19.
 */
public interface IdempotentProcessor<I extends Request, O extends Response> extends Processor<I, O> {

    Response get(Request request, Object... args) throws IdempotentException;

    void storage(Request request, Response response, Object... args) throws IdempotentException;
}
