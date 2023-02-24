package com.springboot.analysis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // default는 class. 컴파일된 class 파일까지 존재 후 runtime시 사라짐. runtime까지 annotation 정보가 유지되도록 설정
@Target(ElementType.TYPE) // Class, interface (including annotation interface), enum, or record declaration
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
