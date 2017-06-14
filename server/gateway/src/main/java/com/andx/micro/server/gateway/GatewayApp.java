package com.andx.micro.server.gateway;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

/**
 * Created by andongxu on 17-5-25.
 */
@SpringBootApplication
@Controller
@EnableZuulProxy
public class GatewayApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApp.class).web(true).run(args);
    }
}
