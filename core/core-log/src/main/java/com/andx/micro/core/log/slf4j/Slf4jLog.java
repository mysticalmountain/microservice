package com.andx.micro.core.log.slf4j;

import com.andx.micro.api.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andongxu on 16-7-28.
 */
public class Slf4jLog implements Log {

    private Logger log;
    private static final String callerFQCN = Slf4jLog.class.getName();

    Slf4jLog(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    Slf4jLog(String name) {
        log = LoggerFactory.getLogger(name);
    }

    public static Slf4jLog getLog(Class<?> clazz) {
        return new Slf4jLog(clazz);
    }

    public static Slf4jLog getLog(String name) {
        return new Slf4jLog(name);
    }

    public void info(String message) {
        log.info(message);
    }

    public void info(String message, Throwable t) {
        log.info(message, t);
    }

    public void debug(String message) {
        log.debug(message);
    }

    public void debug(String message, Throwable t) {
        log.debug(message, t);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void warn(String message, Throwable t) {
        log.warn(message, t);
    }

    public void error(String message) {
        log.error(message);
    }

    public void error(String message, Throwable t) {
        log.error(message, t);
    }

    @Override
    public void fatal(String message) {

    }

    @Override
    public void fatal(String message, Throwable t) {

    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

}
