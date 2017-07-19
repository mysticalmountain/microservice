package com.andx.micro.user.config;

import com.andx.micro.api.core.InitializationProjec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-1.
 */
@Component
public class UserListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    @Qualifier("initializationService")
    InitializationProjec initializationService;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            initializationService.init(null);
        }
    }
}
