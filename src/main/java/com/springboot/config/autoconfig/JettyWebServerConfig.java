package com.springboot.config.autoconfig;

import com.springboot.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
@MyAutoConfiguration
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory") // @Bean을 통해 factory method통해 만들때 기본 이름은 메소드명 servletWebServerFactory. 이름 충돌 방지를 위해 지정
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
