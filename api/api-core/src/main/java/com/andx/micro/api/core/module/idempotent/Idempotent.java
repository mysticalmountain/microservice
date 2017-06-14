package com.andx.micro.api.core.module.idempotent;

import com.andx.micro.api.core.Module;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-19.
 */
public interface Idempotent<I extends Request, O extends Response> extends Module<I, O> {

//    O idempotent(I i, IdempotentProcessor<Request, Response> processor, ReqRsp reqRsp) throws IdempotentException;

    O check(I i, IdempotentProcessor<Request, Response> processor, Object... args) throws IdempotentException;

    O storage(I i, O o, IdempotentProcessor<Request, Response> processor, Object... args) throws IdempotentException;
}
