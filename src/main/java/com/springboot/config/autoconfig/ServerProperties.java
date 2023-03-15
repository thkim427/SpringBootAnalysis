package com.springboot.config.autoconfig;

import com.springboot.config.MyConfigurationProperties;
import org.springframework.stereotype.Component;

// Component scanner / @import(ServerProperties.class)
//@Component
@MyConfigurationProperties(prefix = "server") // namespace 지정 (java의 package 역할)
public class ServerProperties {
    //data holder의 역할로 getter, setter만 사용

    private String contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
