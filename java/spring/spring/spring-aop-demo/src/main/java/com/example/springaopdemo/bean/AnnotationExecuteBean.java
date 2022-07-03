package com.example.springaopdemo.bean;

import com.example.springaopdemo.annotaion.AspectBean;
import org.springframework.stereotype.Component;

/**
 * AnnotationExecuteBean
 */
@Component
@AspectBean
public class AnnotationExecuteBean implements Execute {
    
    @Override
    public void execute() {
        System.out.println("AnnotationExecuteBean executing");
    }
}
