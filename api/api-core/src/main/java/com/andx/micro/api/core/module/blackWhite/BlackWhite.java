package com.andx.micro.api.core.module.blackWhite;

import com.andx.micro.api.core.Module;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 16-12-17.
 */
public interface BlackWhite<I extends Request, O extends Response> extends Module<I, O> {

    Response blackWhite(Request request, BlackWhiteProcessor<Request, Response> processor, Object... args) throws BlackWhiteException;

}
