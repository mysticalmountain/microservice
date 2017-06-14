package com.andx.micro.support.web.controller;

import com.andx.micro.api.core.exception.UnifiedException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.support.web.dispatch.RequestExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andongxu on 17-3-29.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/service/**", produces = "application/json; charset=UTF-8")
public class RequestController {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    private RequestExecute requestExecute;

    //    @RequestMapping(method = RequestMethod.GET, headers="Accept=application/xml, application/json")
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String get(HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        Map<String, String[]> params = new HashMap(request.getParameterMap());
        return requestExecute.execute(request, "GET", servletPath, params);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String post(@RequestBody String body, HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        return requestExecute.execute(request, "POST", servletPath, body);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public
    @ResponseBody
    String put(@RequestBody String body, HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        return requestExecute.execute(request, "PATCH", servletPath, body);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    String delete(HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        Map<String, String[]> params = new HashMap(request.getParameterMap());
        return requestExecute.execute(request, "DELETE", servletPath, params);
    }

    @ExceptionHandler(UnifiedException.class)
    public
    @ResponseBody
    String handleIOException(UnifiedException ue) {
        log.error(ue.getMessage(), ue);
        String result = "{\"errorCode\":" + "999999" + ",\"errorMessage\":" + "\"" + ue.getMessage() + "\"" + ",\"success\":false}";
        log.info(result);
        return result;
    }

    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    String handleIOException(Exception e) {
        log.error(e.getMessage(), e);
        String message = "";
        if (e instanceof HttpMessageNotReadableException) {
            message = "request data is not readable";
        } else {
            message = "unknown error";
        }
        String result = "{\"errorCode\":" + "999999" + ",\"errorMessage\":" + "\"" + message + "\"" + ",\"success\":false}";
        log.info(result);
        return result;
    }

    @RequestMapping("/json2")
    public ResponseEntity<Map<String, String>> testJson2() {
        log.info("get json input from HttpEntity annotation");
        Map<String, String> res = new HashMap<>();
        res.put("k1", "v1");
        ResponseEntity<Map<String, String>> responseResult = new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
//        ResponseEntity<JsonResult> responseResult = new ResponseEntity<JsonResult>(new JsonResult(true, "return ok"), HttpStatus.OK);
        return responseResult;
    }
}
