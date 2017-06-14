package com.andx.micro.user.config;

import com.andx.micro.support.web.filter.PassFilter;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-2.
 */
@Component
public class UserPassFilter implements PassFilter {
    @Override
    public String[] passRegex() {
        return new String[]{
                "^/service/login",
                "^/service/hibbon",
                "^/service/services/init"
        };
    }
}
