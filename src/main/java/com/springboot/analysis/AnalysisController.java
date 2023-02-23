// web의 요청을 받아서 결과를 주는 컴포넌트
package com.springboot.analysis;

import java.util.Objects;

// hello 요청을 받으면 응답을 보내는 controller
// @RestController // html을 통채로 return하지 않고 api 요청에 대한 응답을 body에 특정한 type으로 encoding해서 보내주는 controller
public class AnalysisController {
    // @GetMapping("/hello") // Get으로 된 web 요청, url path가 hello로 시작하는 url만 받는다.

    private final AnalysisService analysisService; // 재할당 불가 final 이므로 정의할때 초기화 해주거나 생성자에서 초기화 필요

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService; // 생성시 parameter로 service를 받아서 멤버 변수에 저장
    }

    public String hello(String name) {
        //SimpleAnalysisService analysisService = new SimpleAnalysisService(); // 직접 service 컴포넌트를 생성해서 사용하는 방식
        // assembler (spring container)에서 bean을 주입
        // - controller 생성 시 생성자 parameter로 service object를 주입
        // - factory method로 bean을 만들면서 매개변수로 넘겨줌
        // - controller class의 property에 setter method를 통해 사용할 object 주입
        // interface를 중간에 두고 의존관계를 맺고 code level 의존 관계를 제거

        // 컨트롤러의 중요한 일 : 요청을 검증하는 일 (name이 null인 경우 error 발생)
        return analysisService.sayHello(Objects.requireNonNull(name));
    }
}

//httpie test : http -v ":8080/hello?name=spring"

//GET /hello?name=spring HTTP/1.1 메소드, 경로, http 버전
//        Accept: */* 모든 컨텐츠 타입을 수용하겠다 (html/json/text...)
//Accept-Encoding: gzip, deflate
//Connection: keep-alive
//Host: localhost:8080
//User-Agent: HTTPie/3.2.1

//HTTP/1.1 200
//Connection: keep-alive
//Content-Length: 12
//Content-Type: text/plain;charset=UTF-8 (html/json/text...) 인코딩 정보
//Date: Wed, 22 Feb 2023 05:37:22 GMT
//Keep-Alive: timeout=60

//Hello spring