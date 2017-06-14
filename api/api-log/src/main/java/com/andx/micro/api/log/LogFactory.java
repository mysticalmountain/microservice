package com.andx.micro.api.log;

/**
 * Created by andongxu on 17-1-3.
 */
public interface LogFactory {

    Log getLog(Class<?> c);

    Log getLog(String name);
}
