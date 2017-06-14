package com.andx.micro.support.web.controller;

import com.andx.micro.support.web.service.login.NoLoginException;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andongxu on 17-4-14.
 */
//@Controller
//@RequestMapping("/error")
public class MicroErrorController extends BasicErrorController {

//    public MicroErrorController(ErrorAttributes errorAttributes) {
//        super(errorAttributes);
//    }

    public MicroErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error", model);
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        Object obj = request.getAttribute("javax.servlet.error.exception");
        ServletException exception = (ServletException) request.getAttribute("javax.servlet.error.exception");
        if (exception instanceof NoLoginException) {
            body.put("errorCode", 999998);
            body.put("errorMessage", exception.getMessage());
        } else {
            body.put("errorCode", "999999");
            body.put("errorMessage", "unknown error");
        }
        body.put("success", false);
//        HttpStatus status = getStatus(request);
        return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
    }


}
