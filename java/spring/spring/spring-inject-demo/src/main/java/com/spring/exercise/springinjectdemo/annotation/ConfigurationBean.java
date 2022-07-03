package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean configuration
 */
@Configuration
@RequiredArgsConstructor
public class ConfigurationBean implements AssembledBean {
    
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @Configuration 注解方式装配的 ConfigurationBean " + properties);
    }
    
    // ------------- ConfigBean 装配
    
    @Bean
    public ConfigBean configBean(AnnotationBeanProperties properties) {
        return new ConfigBean(properties);
    }
    
    @RequiredArgsConstructor
    static class ConfigBean implements AssembledBean {
        private final AnnotationBeanProperties properties;
        
        @Override
        public void show() {
            System.out.println("我是通过 @Bean 注解方式装配的 ConfigBean " + properties);
        }
    }
}
