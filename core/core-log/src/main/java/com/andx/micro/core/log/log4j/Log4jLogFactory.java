package com.andx.micro.core.log.log4j;

import com.andx.micro.api.log.Log;
import com.andx.micro.api.log.LogFactory;

/**
 * Created by andongxu on 16-7-28.
 */
public class Log4jLogFactory implements LogFactory {
    public Log getLog(Class<?> c) {
        return new Log4jLog(c);
    }

    public Log getLog(String name) {
        return new Log4jLog(name);
    }

    private static Log4jLogFactory log4jLogFactory;

    static {
        log4jLogFactory = new Log4jLogFactory();
    }

    public static LogFactory getLogFactory() {
        return log4jLogFactory;
    }

}
