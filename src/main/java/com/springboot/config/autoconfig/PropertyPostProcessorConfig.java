package com.springboot.config.autoconfig;

import com.springboot.config.MyAutoConfiguration;
import com.springboot.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.core.annotation.AnnotationUtils.getAnnotationAttributes;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {

        // 모든 bean object 초기화 이후 프로세스
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotation = findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if(annotation == null) return bean;

                Map<String, Object> attrs = getAnnotationAttributes(annotation);
                String prefix = (String) attrs.get("prefix");

                // binding 시도 후 클래스가 없으면 class 만들어서 return
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
