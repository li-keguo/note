package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * spring bean装配 demo
 */
@Service
@RequiredArgsConstructor
public class ServiceBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @Service 注解方式装配的 ServiceBean " + properties);
    }
}
