package com.example.springaopdemo.advice;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import com.example.springaopdemo.annotaion.AspectBean;
import com.example.springaopdemo.annotaion.AspectBeanApi;
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
public class BeanPointcut implements Pointcut {
    @Override
    @NonNull
    public ClassFilter getClassFilter() {
        return clazz -> {
            if (AnnotationUtil.hasAnnotation(clazz, AspectBeanApi.class)) {
                return true;
            }
            for (Class<?> anInterface : clazz.getInterfaces()) {
                if (AnnotationUtil.hasAnnotation(anInterface, AspectBeanApi.class)) {
                    return true;
                }
            }
            return false;
        };
    }

    @Override
    @NonNull
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return ClassUtil.isPublic(method);
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
