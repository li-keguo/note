package com.spring.exercise.springinjectdemo.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 注解的方式装配bean的属性
 */
@Component
@Data
public class AnnotationBeanProperties {
    
    private String name;
    
    @PostConstruct
    public void initialize() {
        name = "annotation default";
    }
    
    @Override
    public String toString() {
        return "properties{" + "name='" + name + '\'' + '}';
    }
}
