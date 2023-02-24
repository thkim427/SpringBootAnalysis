package com.springboot.analysis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalysisControllerTest {
    @Test
    void analysisController() {
        // 의존 관계가 있는 service로 부터 고립된 테스트
        AnalysisController analysisController = new AnalysisController(name -> name); // test stub 수동 의존성 주입

        String ret = analysisController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsAnalysisController() {
        // 의존성 주입 : 의존 관계가 있는 객체를 제3의 어셈블러가 runtime에 관계를 맺어주는것. Test Code가 Assembler 역할
        AnalysisController analysisController = new AnalysisController(name -> name); // test stub 수동 의존성 주입

        Assertions.assertThatThrownBy(() -> {
            String ret = analysisController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            String ret = analysisController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
