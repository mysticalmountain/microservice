package com.andx.micro.api.core;

/**
 * Created by andongxu on 16-12-16.
 */
public interface Module<I, O> extends Adaptor {

     void notify(I i);
}
