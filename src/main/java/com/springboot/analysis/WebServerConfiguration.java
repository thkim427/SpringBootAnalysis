package com.springboot.analysis;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration(proxyBeanMethods = false) // 관례적으로 bean 사이의 상호 의존 관계 주입이 필요 없다면 false
public class WebServerConfiguration {
    @Bean
    ServletWebServerFactory customWebServerFactory() {
        UndertowServletWebServerFactory serverFactory = new UndertowServletWebServerFactory();
        serverFactory.setPort(9090);
        return serverFactory;
    }
}
