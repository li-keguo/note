package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * spring bean装配 demo
 */
@Controller
@RequiredArgsConstructor
public class ControllerBean implements AssembledBean {
    
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @Controller 注解方式装配的 ControllerBean " + properties);
    }
}
