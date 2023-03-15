package com.springboot.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Import(MyConfigurationPropertiesImportSelector.class) // enable annotation 패턴 기능을 가진 config, import selector 땡겨오기 위해
public @interface EnableMyConfigurationProperties {
    Class<?> value();
}
