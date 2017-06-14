package com.andx.micro.api.core.chain;

import com.andx.micro.api.core.module.service.SampleService;

/**
 * Created by andongxu on 16-12-17.
 */
public interface ModuleChain<I, O> {

    void initChain(ModuleChain<I, O> chain);

    O chain(I i, SampleService<I, O> processor, Object... args);

}
