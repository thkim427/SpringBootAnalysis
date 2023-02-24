package com.springboot.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

//@SpringBootApplication
@Configuration // Spring Container가 Bean 구성정보를 가지고 있는 Class임을 인식하도록 @Bean Factory Method 사용 시 Class level에 붙여줘야함
public class AnalysisApplication {
    // Spring Bean이 다른 Bean을 의존(사용) 하고 있다면 Spring Container에 구성 정보로 제공해줘야함
    // - 외부 설정파일 이용 (과거의 방식)
    // - Factory Method 이용해서 Bean 생성 및 의존관계 주입 (Java code가 설정 정보보다 간결하고 이해하기 쉽다)

    @Bean // Spring Container가 Bean Factory Method로 인식
    public AnalysisController analysisController(AnalysisService analysisService) { // 일반적인 의존 Object 매개변수로 넘겨주는 방식
        return new AnalysisController(analysisService);
    }

    @Bean
    public AnalysisService analysisService() { // 의존성 주입을 위해 interface type으로 return
        return new SimpleAnalysisService();
    }

    public static void main(String[] args) {
        // 스프링 컨테이너를 대표하는 interface = application context : application의 정보 총괄. 어떤 bean 사용할것인가, resource에 접근하는 방법, 내부 event를 전달하고 받는 방법...
        //GenericApplicationContext applicationContext = new GenericApplicationContext();
        //GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() { // 생성자 사용시 코드블럭을 붙여서 익명클래스(일회용품) 활용
        AnnotationConfigServletWebApplicationContext applicationContext = new AnnotationConfigServletWebApplicationContext() { // GenericApplicationContext는 Java 코드로 만든 구성 정보를 읽을 수 없음
            @Override
            protected void onRefresh() { // GenericWebApplicationContext가 사용중인 onRefresh 메소드를 Override (메소드 기능확장)
                super.onRefresh();

                // Spring Boot의 설계대로 refresh 작업 시 Dispatcher Servlet 생성
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this) // 생성전 확장하는 클래스 내부에서 자기자신을 참조
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };

        // 스프링 컨테이너는 일반적으로 어떤 클래스를 이용해서 bean object를 이용 할것인가 meta 정보를 넣어주는 방식으로 구성
        //applicationContext.registerBean(AnalysisController.class); // 일반적으로 bean class 정보만 주는 방식 사용
        //applicationContext.registerBean(SimpleAnalysisService.class); // spring container가 controller 생성시 AnalysisService를 구현한 SimpleAnalysisService를 생성자 매개변수로 주입 (register 순서 상관 없음)
        applicationContext.register(AnalysisApplication.class); // AnalysisApplication의 Java 코드로 된 구성 정보를 등록
        applicationContext.refresh(); // bean object 생성 및 초기화


        //SpringApplication.run(AnalysisApplication.class, args);
        //System.out.println("Containerless Standalone Application");
//        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // tomcat servlet web server 만들어 주는 클래스
//        WebServer webServer = serverFactory.getWebServer(servletContext -> { // servlet container에 servlet을 등록, @FunctionalInterface이므로 lambda식으로 변형 가능
            //AnalysisController analysisController = new AnalysisController(); // spring container를 통해 생성하도록 변경

//            servletContext.addServlet(/*"hello"*/"Front-Controller", new HttpServlet() { // servlet interface 자리에 공통적인 코드를 미리 구현해놓고 상속해서 사용하는 adapter 클래스를 override
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//                    // front-controller를 통해 인증, 보안, 다국어, 공통기능... 수행
//                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {  // servlet mapping
//                        //super.service(req, resp);
//                        String name = req.getParameter("name"); // 요청의 매개변수 name을 받음
//
//                        AnalysisController analysisController = applicationContext.getBean(AnalysisController.class); // 필요할때 spring container가 가지고있는 bean을 사용
//                        String ret = analysisController.hello(name); // parameter 값을 추출해서 복잡한 binding 작업 (타입변환까지)
//
//                        // 응답구성 : 상태코드, 상태값, 컨텐츠타입, 헤더, 바디
//                        //resp.setStatus(200);
//                        //esp.setStatus(HttpStatus.OK.value()); // 성공시 default 200이므로 생략가능
//                        //resp.setHeader("Content-Type", "text/plain");
//                        //resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // 오타의 위험, 관리의 편의로 spring에서 제공하는 enum으로 변경
//                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE); // content-type 지정 메소드 활용
//                        //resp.getWriter().println("Hello " + name); // Body에 문자열 응답을 주기 위한 writer
//                        resp.getWriter().println(ret); // web 응답을 만드는데에 controller 활용
//                    }
//                    else if (req.getRequestURI().equals("/user")) {
//
//                    }
//                    else {
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//
//                }
//          }).addMapping("/*"); // servlet 컨테이너가 요청을 맵핑해줄 url 필요

            // DispatcherServlet : spring 최초 구현시점부터 존재한 front-controller의 기능들을 제공하는 servlet
//            servletContext.addServlet("dispatcherServlet",
//                    new DispatcherServlet(applicationContext) // 매개변수로 WebApplicationContext를 받음
//            ).addMapping("/*"); // servlet 컨테이너가 요청을 맵핑해줄 url 필요


//        }); // servlet container 생성
//        webServer.start(); // tomcat 시작
    }

}
