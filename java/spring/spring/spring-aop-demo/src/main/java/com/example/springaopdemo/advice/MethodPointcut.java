package com.example.springaopdemo.advice;

import cn.hutool.core.annotation.AnnotationUtil;
import com.example.springaopdemo.annotaion.AspectMethodApi;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * SignAuthFeignMethodPointcut
 *
 * @author likeguo
 */
@Component
public class MethodPointcut implements Pointcut {
    @Override
    @NonNull
    public ClassFilter getClassFilter() {
        return clazz -> true;
    }

    @Override
    @NonNull
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return AnnotationUtil.hasAnnotation(method, AspectMethodApi.class);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass, @NonNull Object... args) {
                return false;
            }
        };
    }
}
