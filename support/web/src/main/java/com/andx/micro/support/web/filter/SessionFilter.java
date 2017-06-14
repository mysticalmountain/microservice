package com.andx.micro.support.web.filter;

import com.andx.micro.support.web.service.login.NoLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 16-11-16.
 */
@Configuration
@WebFilter(displayName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    @Value("${session.filter.enable:false}")
    private Boolean enableSessionFilter;

    @Autowired(required = false)
    private PassFilter passFilter;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        if (enableSessionFilter) {
            boolean isFilter = true;
            if (passFilter == null) {
                isFilter = false;
            }
//            String[] notFilterRegexs = new String[]{"^/views/user/login.html", "^/views/common/\\w+", "^/service/login", "^/resources/\\w+", "^/service/owners/\\w+/resources/\\w+"};
            String[] notFilterRegexs = passFilter.passRegex();
            if (notFilterRegexs != null) {
                for (String notFilterRegex : notFilterRegexs) {
                    Pattern pattern = Pattern.compile(notFilterRegex);
                    Matcher matcher = pattern.matcher(req.getRequestURI());
                    if (matcher.find()) {
                        isFilter = false;
                        break;
                    }
                }
            } else {
                isFilter = false;
            }
            if (isFilter) {
                if (req.getSession().getAttribute("userId") == null) {
                    throw new NoLoginException("no session or session timeout");
                } else {
                    chain.doFilter(req, rsp);
                }
            } else {
                chain.doFilter(req, rsp);
            }
        } else {
            chain.doFilter(req, rsp);
        }

    }

    @Override
    public void destroy() {

    }
}
