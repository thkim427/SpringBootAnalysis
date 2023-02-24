package com.springboot.analysis;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// web의 요청을 받아서 로직을 수행하는 컴포넌트 (bean)
@MyComponent // bean
@Service
public class SimpleAnalysisService implements AnalysisService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
