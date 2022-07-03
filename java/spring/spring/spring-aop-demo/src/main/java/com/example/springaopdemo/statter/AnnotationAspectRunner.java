package com.example.springaopdemo.statter;

import com.example.springaopdemo.bean.AnnotationExecuteBean;
import com.example.springaopdemo.bean.AnnotationExecuteBeanApi;
import com.example.springaopdemo.bean.AnnotationExecuteMethod;
import com.example.springaopdemo.bean.AnnotationExecuteMethodApi;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * AnnotationAspectRunner
 */
@Component
@RequiredArgsConstructor
public class AnnotationAspectRunner implements ApplicationRunner {
    
    private final AnnotationExecuteBean executeBean;
    
    private final AnnotationExecuteMethod executeMethod;
    
    private final AnnotationExecuteBeanApi executeBeanApi;
    
    private final AnnotationExecuteMethodApi executeMethodApi;
    
    @Override
    public void run(ApplicationArguments args) {
        executeBean.execute();
        executeMethod.execute();
        executeBeanApi.execute();
        executeMethodApi.execute();
    }
    
}
