package com.andx.micro.support.web.controller;

import com.andx.micro.api.core.exception.UnifiedException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.Constant;
import com.andx.micro.support.web.dispatch.RequestExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
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

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String get(HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        Map<String, String[]> params = new HashMap(request.getParameterMap());
        Map<String, String> target = convertParams(params);
        log.info(String.format(" ------ GET ------ request[%s]", target));
        return requestExecute.execute(request, "GET", servletPath, params);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String post(@RequestBody String body, HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        log.info(String.format(" ------ POST ------ request[%s]", body));
        return requestExecute.execute(request, "POST", servletPath, body);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    String put(@RequestBody String body, HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        log.info(String.format(" ------ PUT ------ request[%s]", body));
        return requestExecute.execute(request, "PUT", servletPath, body);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public
    @ResponseBody
    String patch(@RequestBody String body, HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        log.info(String.format(" ------ PATCH ------ request[%s]", body));
        return requestExecute.execute(request, "PATCH", servletPath, body);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    String delete(HttpServletRequest request) throws Exception {
        String servletPath = request.getServletPath();
        Map<String, String[]> params = new HashMap(request.getParameterMap());
        Map<String, String> target = convertParams(params);
        log.info(String.format(" ------ DELETE ------ request[%s]", target));
        return requestExecute.execute(request, "DELETE", servletPath, params);
    }

    @ExceptionHandler(UnifiedException.class)
    public
    @ResponseBody
    String handleIOException(UnifiedException ue) {
//        log.error(ue.getMessage(), ue);
        String result = String.format("{'errorCode':%s, 'errorMessage':%s, 'success':%b}", "999999", ue.getMessage(), false);
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
        String result = String.format("{'errorCode':%s, 'errorMessage':%s, 'success':%b}", "999999", message, false);
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

    private Map<String, String> convertParams(Map<String, String []> source) {
        Iterator<String> iterator = source.keySet().iterator();
        Map<String, String> target = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String[] vals = source.get(key);
            if (vals.length > 1) {
                String value = "";
                for (String val : vals) {
                    if (value.equals("")) {
                        value += val;
                    } else {
                        value += "," + val;
                    }
                }
                target.put(key, value);
            } else {
                target.put(key, vals[0]);
            }
        }
        return target;
    }
}
