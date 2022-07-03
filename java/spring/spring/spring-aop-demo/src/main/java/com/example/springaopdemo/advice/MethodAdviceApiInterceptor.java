package com.example.springaopdemo.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * MethodAdviceApiInterceptor
 */
@Aspect
@Component
public class MethodAdviceApiInterceptor implements MethodInterceptor {
    
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("MethodAdviceApiInterceptor proxy execute " + invocation.getMethod().getName());
        return invocation.proceed();
    }
}
