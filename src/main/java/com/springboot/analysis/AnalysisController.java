// hello 요청을 받으면 응답을 보내는 controller
package com.springboot.analysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// web의 요청을 받아서 결과를 주는 컴포넌트
@RestController // html을 통채로 return하지 않고 api 요청에 대한 응답을 body에 특정한 type으로 encoding해서 보내주는 controller
public class AnalysisController {
    @GetMapping("/hello") // Get으로 된 web 요청, url path가 hello로 시작하는 url만 받는다.
    public String hello(String name) {
        return "Hello " + name;
    }
}
