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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
            AnalysisController analysisController = new AnalysisController();

            servletContext.addServlet(/*"hello"*/"Front-Controller", new HttpServlet() { // servlet interface 자리에 공통적인 코드를 미리 구현해놓고 상속해서 사용하는 adapter 클래스를 override
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                    // front-controller를 통해 인증, 보안, 다국어, 공통기능... 수행
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {  // servlet mapping
                        //super.service(req, resp);
                        String name = req.getParameter("name"); // 요청의 매개변수 name을 받음

                        String ret = analysisController.hello(name);

                        // 응답구성 : 상태코드, 상태값, 컨텐츠타입, 헤더, 바디
                        //resp.setStatus(200);
                        resp.setStatus(HttpStatus.OK.value()); // 오타의 위험, 관리의 편의로 spring에서 제공하는 enum으로 변경
                        //resp.setHeader("Content-Type", "text/plain");
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // 오타의 위험, 관리의 편의로 spring에서 제공하는 enum으로 변경
                        //resp.getWriter().println("Hello " + name); // Body에 문자열 응답을 주기 위한 writer
                        resp.getWriter().println(ret); // web 응답을 만드는데에 controller 활용
                    }
                    else if (req.getRequestURI().equals("/user")) {

                    }
                    else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                }
            }).addMapping("/*"); // servlet 컨테이너가 요청을 맵핑해줄 url 필요
        }); // servlet container 생성
        webServer.start(); // tomcat 시작
    }

}
