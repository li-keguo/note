package com.spring.exercise.springinjectdemo.annotation;

import com.spring.exercise.springinjectdemo.common.AnnotationBeanProperties;
import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * spring bean装配 demo
 */
@Repository
@RequiredArgsConstructor
public class RepositoryBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 @Repository 注解方式装配的 RepositoryBean " + properties);
    }
}
