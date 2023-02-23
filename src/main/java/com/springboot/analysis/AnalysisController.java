// web의 요청을 받아서 결과를 주는 컴포넌트
package com.springboot.analysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// hello 요청을 받으면 응답을 보내는 controller
// @RestController // html을 통채로 return하지 않고 api 요청에 대한 응답을 body에 특정한 type으로 encoding해서 보내주는 controller
public class AnalysisController {
    // @GetMapping("/hello") // Get으로 된 web 요청, url path가 hello로 시작하는 url만 받는다.
    public String hello(String name) {
        return "Hello " + name;
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