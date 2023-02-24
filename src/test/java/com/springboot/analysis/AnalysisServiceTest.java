package com.springboot.analysis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD}) // meta annotation으로 사용 가능하도록
@Test
@interface UnitTest {
}

public class AnalysisServiceTest {
    @FastUnitTest
    void simpleAnalysisService() {
        SimpleAnalysisService analysisService = new SimpleAnalysisService();

        String ret = analysisService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void analysisDecorator() {
        AnalysisDecorator decorator = new AnalysisDecorator(name -> name);

        String ret = decorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
