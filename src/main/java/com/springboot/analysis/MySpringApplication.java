package com.springboot.analysis;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        //GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() { // 생성자 사용시 코드블럭을 붙여서 익명클래스(일회용품) 활용
        AnnotationConfigServletWebApplicationContext applicationContext = new AnnotationConfigServletWebApplicationContext() { // GenericApplicationContext는 Java 코드로 만든 구성 정보를 읽을 수 없음
            @Override
            protected void onRefresh() { // GenericWebApplicationContext가 사용중인 onRefresh 메소드를 Override (메소드 기능확장)
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // dispatcherServlet.setApplicationContext(this); // dispatcher servlet 등록 시점에 application context를 주입 (ApplicationContextAware Interface)

                // Spring Boot의 설계대로 refresh 작업 시 Dispatcher Servlet 생성
                //TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet
                            //new DispatcherServlet(this) // 생성전 확장하는 클래스 내부에서 자기자신을 참조
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };


        // 스프링 컨테이너는 일반적으로 어떤 클래스를 이용해서 bean object를 이용 할것인가 meta 정보를 넣어주는 방식으로 구성
        //applicationContext.registerBean(AnalysisController.class); // 일반적으로 bean class 정보만 주는 방식 사용
        //applicationContext.registerBean(SimpleAnalysisService.class); // spring container가 controller 생성시 AnalysisService를 구현한 SimpleAnalysisService를 생성자 매개변수로 주입 (register 순서 상관 없음)
        //applicationContext.register(AnalysisApplication.class); // AnalysisApplication의 Java 코드로 된 구성 정보를 등록
        applicationContext.register(applicationClass); // AnalysisApplication의 Java 코드로 된 구성 정보를 등록
        applicationContext.refresh(); // bean object 생성 및 초기화
    }
}
