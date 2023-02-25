package com.springboot.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private  ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader){
        this.classLoader = classLoader;
    }
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);// application class path file reader
        return StreamSupport.stream(candidates.spliterator(),false).toArray(String[]::new);

        /*List<String> autoConfigs = new ArrayList<>();
        for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)){
            autoConfigs.add(candidate);
        }
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidates ->
            autoConfigs.add(candidates);
        );

        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]); // 작은 array를 주면 무시하고 새로 array를 만든 후 값을 복사
        return autoConfigs.stream().toArray(String[]::new); // toArray에 생성자 reference 전달
        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);*/

        /*return new String[] {
                "com.springboot.config.autoconfig.DispatcherServletConfig",
                "com.springboot.config.autoconfig.TomcatWebServerConfig"
        };*/
    }
}
