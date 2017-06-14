package com.andx.micro.api.log;

/**
 * Created by andongxu on 17-1-3.
 */
public interface Log {

     void debug(String message);

     void debug(String message, Throwable t);

     void info(String message);

     void info(String message, Throwable t);

     void warn(String message);

     void warn(String message, Throwable t);

     void error(String message);

     void error(String message, Throwable t);

     void fatal(String message);

     void fatal(String message, Throwable t);

     boolean isDebugEnabled();

     boolean isInfoEnabled();

     boolean isWarnEnabled();

     boolean isErrorEnabled();

     boolean isFatalEnabled();
}
