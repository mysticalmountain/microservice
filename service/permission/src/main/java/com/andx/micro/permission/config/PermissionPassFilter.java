package com.andx.micro.permission.config;

import com.andx.micro.support.web.filter.PassFilter;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-2.
 */
@Component
public class PermissionPassFilter implements PassFilter {
    @Override
    public String[] passRegex() {
        return new String[]{
                "^/service/owners/\\w+/resources/\\w+",
                "^/service/services/\\w+/noPermission$",
                "^/service/permissions$",
                "^/service/services/init$"
        };
    }
}
