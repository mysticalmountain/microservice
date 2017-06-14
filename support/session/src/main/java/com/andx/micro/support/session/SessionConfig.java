package com.andx.micro.support.session;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * Created by andongxu on 17-4-17.
 */
@EnableJdbcHttpSession
public class SessionConfig {

//        @Bean
//        public PlatformTransactionManager transactionManager(DataSource dataSource) {
//                return new DataSourceTransactionManager(dataSource);
//        }
}
