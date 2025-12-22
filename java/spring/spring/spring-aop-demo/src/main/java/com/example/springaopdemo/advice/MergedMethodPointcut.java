package com.example.springaopdemo.advice;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * SignAuthFeignMethodPointcut
 *
 * @author likeguo
 */
public abstract class MergedMethodPointcut<T extends Annotation> implements Pointcut {

    @Override
    @NonNull
    public ClassFilter getClassFilter() {
        return clazz -> {
            if (hasAnnotation(clazz)) {
                return true;
            }
            for (Method method : clazz.getDeclaredMethods()) {
                if (hasAnnotation(method)) {
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
                return hasAnnotation(method) || hasAnnotation(targetClass);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass, @NonNull Object... args) {
                return matches(method, targetClass);
            }
        };
    }

    /**
     * has annotation
     *
     * @param element element
     * @return boolean
     */
    protected boolean hasAnnotation(AnnotatedElement element) {
        return AnnotatedElementUtils.findMergedAnnotation(element, mergedAnnotation()) != null;
    }

    /**
     * target merged annotation
     *
     * @return merged annotation
     */
    abstract Class<T> mergedAnnotation();
}
