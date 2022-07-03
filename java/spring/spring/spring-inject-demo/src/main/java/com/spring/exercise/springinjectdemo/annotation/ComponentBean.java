package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * spring bean装配 demo
 */
@Component
@RequiredArgsConstructor
public class ComponentBean implements AssembledBean {
    
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @Component 注解方式装配的 ComponentBean " + properties);
    }
}
