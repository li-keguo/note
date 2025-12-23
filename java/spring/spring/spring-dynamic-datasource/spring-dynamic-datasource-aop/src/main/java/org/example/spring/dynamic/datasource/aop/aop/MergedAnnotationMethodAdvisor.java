package org.example.spring.dynamic.datasource.aop.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The type Merged annotation advisor.
 *
 * @param <T> the type parameter
 */
public abstract class MergedAnnotationMethodAdvisor<T extends Annotation> extends DefaultPointcutAdvisor {

    /**
     * Instantiates a new Merged annotation advisor.
     */
    public MergedAnnotationMethodAdvisor() {
        this.setPointcut(new MergedAnnotationMethodPointcutImpl());
        this.setAdvice(new MergedAnnotationMethodAdviceApiInterceptorImpl());
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

    /**
     * The type Merged annotation method advice api interceptor.
     */
    class MergedAnnotationMethodAdviceApiInterceptorImpl extends MergedAnnotationMethodAdviceApiInterceptor<T> {

        @Override
        protected Object doInvoker(MethodInvocation invocation, T annotation) {
            return MergedAnnotationMethodAdvisor.this.doInvoker(invocation, annotation);
        }

        @Override
        public Class<T> mergedAnnotation() {
            return MergedAnnotationMethodAdvisor.this.mergedAnnotation();
        }
    }

    /**
     * The type Merged annotation method pointcut.
     */
    class MergedAnnotationMethodPointcutImpl extends MergedAnnotationMethodPointcut<T> {

        @Override
        protected Class<T> mergedAnnotation() {
            return MergedAnnotationMethodAdvisor.this.mergedAnnotation();
        }
    }

}
