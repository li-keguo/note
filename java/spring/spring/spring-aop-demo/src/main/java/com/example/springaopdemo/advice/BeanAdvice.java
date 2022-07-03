package com.example.springaopdemo.advice;

import com.example.springaopdemo.annotaion.AspectBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * BeanAdvice
 */
@Aspect
@Component
public class BeanAdvice {
    
    
    @Pointcut("@within(com.example.springaopdemo.annotaion.AspectBean)")
    public void pointcut() {
        // default implementation ignored
    }
    
    @Around("pointcut() && @within(aspectBean)")
    public Object advice(ProceedingJoinPoint joinPoint, AspectBean aspectBean) throws Throwable {
        System.out.println("BeanAdvice proxy execute " + joinPoint.getSignature().getName());
        System.out.println("annotation " + aspectBean.getClass().getName());
        return joinPoint.proceed();
    }
}
