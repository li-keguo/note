package com.example.springaopdemo.advice;

import com.example.springaopdemo.annotaion.AspectMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * MethodAdvice
 */
@Aspect
@Component
public class MethodAdvice {
    
    
    @Pointcut("@annotation(com.example.springaopdemo.annotaion.AspectMethod)")
    public void pointcut() {
        // default implementation ignored
    }
    
    @Around("pointcut() && @annotation(aspectMethod)")
    public Object advice(ProceedingJoinPoint joinPoint, AspectMethod aspectMethod) throws Throwable {
        System.out.println("MethodAdvice proxy execute " + joinPoint.getSignature().getName());
        System.out.println("annotation " + aspectMethod.getClass().getName());
        return joinPoint.proceed();
    }
}
