package com.springboot.analysis;

// web의 요청을 받아서 로직을 수행하는 컴포넌트 (bean)
public class SimpleAnalysisService {
    String sayHello(String name) {
        return "Hello " + name;
    }
}
