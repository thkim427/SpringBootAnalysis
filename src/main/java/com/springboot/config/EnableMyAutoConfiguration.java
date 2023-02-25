package com.springboot.config;

import com.springboot.config.autoconfig.DispatcherServletConfig;
import com.springboot.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // TYPE : class interface enum... meta annotation으로 사용 가능
//@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class}) // @Component 클래스를 지정해주면 구성정보로 직접 추가 가능
@Import(MyAutoConfigImportSelector.class) // import select를 구현한 method의 결과 config class loading
public @interface EnableMyAutoConfiguration {
}
