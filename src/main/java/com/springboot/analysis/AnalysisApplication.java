package com.springboot.analysis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
//import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

//@SpringBootApplication
public class AnalysisApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AnalysisApplication.class, args);
        //System.out.println("Containerless Standalone Application");
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // tomcat servlet web server 만들어 주는 클래스
        WebServer webServer = serverFactory.getWebServer(servletContext -> { // servlet container에 servlet을 등록, @FunctionalInterface이므로 lambda식으로 변형 가능
            servletContext.addServlet("hello", new HttpServlet() { // servlet interface 자리에 공통적인 코드를 미리 구현해놓고 상속해서 사용하는 adapter 클래스를 override
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //super.service(req, resp);
                    String name = req.getParameter("name"); // 요청의 매개변수 name을 받음
                    // 응답구성 : 상태코드, 상태값, 컨텐츠타입, 헤더, 바디
                    resp.setStatus(200);
                    //resp.setHeader("Content-Type", "text/plain");
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // 오타의 위험, 관리의 편의로 spring에서 제공하는 enum으로 변경
                    resp.getWriter().println("Hello " + name); // Body에 문자열 응답을 주기 위한 writer
                }
            }).addMapping("/hello"); // servlet 컨테이너가 요청을 맵핑해줄 url 필요
        }); // servlet container 생성
        webServer.start(); // tomcat 시작
    }

}
