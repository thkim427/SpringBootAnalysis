package com.springboot.analysis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalysisServiceTest {
    @Test
    void simpleAnalysisService() {
        SimpleAnalysisService analysisService = new SimpleAnalysisService();

        String ret = analysisService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }
}
