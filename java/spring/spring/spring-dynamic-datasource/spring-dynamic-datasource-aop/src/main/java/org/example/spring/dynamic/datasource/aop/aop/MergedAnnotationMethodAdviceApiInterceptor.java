package org.example.spring.dynamic.datasource.aop.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * MethodAdviceApiInterceptor
 *
 * @param <T> the type parameter
 */
public abstract class MergedAnnotationMethodAdviceApiInterceptor<T extends Annotation> implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        T annotation = getAnnotation(method);
        try {
            return doInvoker(invocation, annotation);
        } catch (Exception ex) {
            throw ex;
        } finally {
            doFinally(invocation, annotation);
        }
    }

    /**
     * Do finally.
     *
     * @param invocation the invocation
     * @param annotation the annotation
     */
    public void doFinally(MethodInvocation invocation, T annotation) {

    }

    /**
     * Do invoker object.
     *
     * @param invocation the invocation
     * @param annotation the annotation
     * @return the object
     */
    protected abstract Object doInvoker(MethodInvocation invocation, T annotation);

    /**
     * Merged annotation class.
     *
     * @return the class
     */
    @SuppressWarnings("unchecked")
    public Class<T> mergedAnnotation() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    private T getAnnotation(Method method) {
        T annotation = AnnotatedElementUtils.findMergedAnnotation(method, mergedAnnotation());
        if (annotation != null) {
            return annotation;
        }
        return AnnotatedElementUtils.findMergedAnnotation(method.getClass(), mergedAnnotation());
    }

}
