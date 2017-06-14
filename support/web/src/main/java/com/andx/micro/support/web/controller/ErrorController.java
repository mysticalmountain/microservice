package com.andx.micro.support.web.controller;

import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andongxu on 17-4-14.
 */
@ControllerAdvice
public class ErrorController {

    private Log log = Log4jLogFactory.getLogFactory().getLog(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String noLogin(Exception e) {
        log.debug(e.getMessage(), e);
        String result = "{\"errorCode\":" + "999999" + ",\"errorMessage\":" + "\"" + e.getMessage() + "\"" + ",\"success\":false}";
        log.info(result);
        return result;
    }
}
