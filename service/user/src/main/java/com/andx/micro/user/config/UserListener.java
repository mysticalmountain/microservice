package com.andx.micro.user.config;

import com.andx.micro.api.core.InitializationProjec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-5-1.
 */
@Component
public class UserListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    @Qualifier("initializationService")
    InitializationProjec initializationService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        initializationService.init(null);
    }
}
