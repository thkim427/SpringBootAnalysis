package com.springboot.analysis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

//@SpringBootApplication
public class AnalysisApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AnalysisApplication.class, args);
        //System.out.println("Containerless Standalone Application");
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // tomcat servlet web server 만들어 주는 클래스
        WebServer webServer = serverFactory.getWebServer(); // 웹서버 생성
        webServer.start(); // tomcat 시작
    }

}
