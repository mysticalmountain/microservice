package com.andx.micro.core.log.jdk;

import com.andx.micro.api.log.Log;
import com.andx.micro.api.log.LogFactory;

/**
 * Created by andongxu on 16-7-28.
 */
public class JdkLogFactory implements LogFactory {
    public Log getLog(Class<?> c) {
        return new JdkLog(c);
    }

    public Log getLog(String name) {
        return new JdkLog(name);
    }
}
