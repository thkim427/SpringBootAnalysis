package com.springboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
    @Test
    void conditional() {
        //true
        /*AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config1.class);
        ac.refresh();
        MyBean bean = ac.getBean(MyBean.class);*/

        ApplicationContextRunner contextRunner = new ApplicationContextRunner(); // test 목적으로 만들어진 test전용 application context 사용
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class); // abstract class의 method chaining 기법으로 parameter type에 따라 사용가능 메소드 변경
                    assertThat(context).hasSingleBean(Config1.class);
                }); // context type의 lambda를 사용 가능

        //false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class); // abstract class의 method chaining 기법으로 parameter type에 따라 사용가능 메소드 변경
                    assertThat(context).doesNotHaveBean(Config2.class);
                }); // context type의 lambda를 사용 가능
    }

    /*@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface  TrueConditional {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface  FalseConditional {}*/

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface  BooleanConditional {
        boolean value();
    }

    @Configuration
    //@Conditional(TrueCondition.class)
    //@TrueConditional
    @BooleanConditional(value = true)
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    //@Conditional(FalseCondition.class)
    //@FalseConditional
    @BooleanConditional(false) // 속성이 하나뿐이고 value 경우 이름 생략 가능
    static class Config2 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    static class MyBean {}

    /*private static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    private static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }*/

    private static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // java element의 이름을 가져옴 defalut element : value
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean) annotationAttributes.get("value");

            return value;
        }
    }
}
