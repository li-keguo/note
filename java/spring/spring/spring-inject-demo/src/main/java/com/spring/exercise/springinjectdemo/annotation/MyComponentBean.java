package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import com.spring.exercise.springinjectdemo.common.MyComponent;
import lombok.RequiredArgsConstructor;

/**
 * spring bean装配 demo
 */
@MyComponent
@RequiredArgsConstructor
public class MyComponentBean implements AssembledBean {
    
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @MyComponent 注解方式装配的 MyComponentBean " + properties);
    }
}
