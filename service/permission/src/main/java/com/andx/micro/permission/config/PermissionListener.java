package com.andx.micro.permission.config;

import com.andx.micro.api.core.InitializationProjec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-1.
 */
@Component
public class PermissionListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    @Qualifier("initializationService")
    InitializationProjec initializationService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        initializationService.init(null);

    }
}
