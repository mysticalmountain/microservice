package com.andx.micro.console;

import com.andx.micro.support.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 17-2-15.
 */
@SpringBootApplication
@Import({WebConfig.class})
public class WebApp {

    public static void main(String [] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
