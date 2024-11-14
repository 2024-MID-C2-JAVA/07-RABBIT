package com.bank.management.config;

import com.bank.management.interceptor.DinHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DinHeaderInterceptor dinHeaderInterceptor;

    public WebConfig(DinHeaderInterceptor dinHeaderInterceptor) {
        this.dinHeaderInterceptor = dinHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dinHeaderInterceptor).addPathPatterns("/**");
    }
}
