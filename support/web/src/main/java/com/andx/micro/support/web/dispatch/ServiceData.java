package com.andx.micro.support.web.dispatch;


/**
 * Created by andongxu on 9/11/16.
 */
public interface ServiceData<D> {

    public D get() throws DispatchException;


}
