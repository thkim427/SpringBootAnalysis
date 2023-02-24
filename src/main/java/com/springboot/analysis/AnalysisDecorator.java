package com.springboot.analysis;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Proxy Pattern : 서비스 로직 접근제어. Lazy Loading, Remote Access, 보안 기능...
// Decorator Pattern : 여러 기능과 책임을 추가 (부가적인 기능)
@Service // spring의 component로 등록
@Primary // decorator가 여러개일 경우에는 java code 명시하는 방식으로 변경
public class AnalysisDecorator implements AnalysisService {
    private final AnalysisService analysisService;

    public AnalysisDecorator(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + analysisService.sayHello(name) + "*";
    }
}
