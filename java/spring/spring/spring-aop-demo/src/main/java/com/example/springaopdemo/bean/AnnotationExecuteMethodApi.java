package com.example.springaopdemo.bean;

import com.example.springaopdemo.annotaion.AspectMethodApi;
import org.springframework.stereotype.Component;

/**
 * AnnotationExecuteMethodApi
 */
@Component
public class AnnotationExecuteMethodApi implements Execute {
    
    @Override
    @AspectMethodApi
    public void execute() {
        System.out.println("AnnotationExecuteMethodApi executing");
    }
}
