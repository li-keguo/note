package com.example.springaopdemo.bean;

import com.example.springaopdemo.annotaion.AspectBeanApi;
import org.springframework.stereotype.Component;

/**
 * AnnotationExecuteBeanApi
 */
@Component
@AspectBeanApi
public class AnnotationExecuteBeanApi implements Execute {
    
    @Override
    public void execute() {
        System.out.println("AnnotationExecuteBeanApi executing");
    }
}
