package com.andx.micro.api.core.chain;

/**
 * Created by andongxu on 16-12-19.
 */
public abstract class GenericChain<I, O> implements ModuleChain<I, O> {

    protected ModuleChain<I, O> nextChain;

    public void initChain(ModuleChain<I, O> nextChain) {
        this.nextChain = nextChain;
    }
}
