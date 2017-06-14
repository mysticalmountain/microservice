package com.andx.micro.permission;

import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 17-1-7.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.andx.micro.permission", "com.andx.micro.core.service"})
@Import({JpaConfig.class, ValidatorConfig.class})
public class Config {
}
