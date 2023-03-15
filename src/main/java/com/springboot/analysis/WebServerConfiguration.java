/*package com.springboot.analysis;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration(proxyBeanMethods = false) // 관례적으로 bean 사이의 상호 의존 관계 주입이 필요 없다면 false
public class WebServerConfiguration {

    // 유저 구성정보가 항상 자동 구성정보보다 우선되기때문에 @ConditionalOnMissingBean 안전.
    // 자동 구성정보간 생성 순서는 주의해야함
    @Bean
    ServletWebServerFactory customWebServerFactory() {
        UndertowServletWebServerFactory serverFactory = new UndertowServletWebServerFactory();
        serverFactory.setPort(9090);
        return serverFactory;
    }
}*/
