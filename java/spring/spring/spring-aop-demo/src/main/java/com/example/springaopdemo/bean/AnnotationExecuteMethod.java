package com.example.springaopdemo.bean;

import com.example.springaopdemo.annotaion.AspectMethod;
import org.springframework.stereotype.Component;

/**
 * AnnotationExecuteMethod
 */
@Component
public class AnnotationExecuteMethod implements Execute {
    
    @Override
    @AspectMethod
    public void execute() {
        System.out.println("AnnotationExecuteMethod executing");
    }
}
