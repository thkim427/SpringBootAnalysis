package com.springboot.analysis;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션의 두가지 필수요소 유지기간, 적용대상
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명주기 (어노테이션이 언제까지 살아있을것인가)
@Target(ElementType.TYPE) // Class, Interface 같은 Type에만 적용
@Component // Spring Container의 Bean Object로 등록이 된다. 어떤 종류인지, 어떤 역할인지 어노테이션을 통해 표현하고 싶을때. (DAO, Service, Controller...)
public @interface MyComponent {
}
