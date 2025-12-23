package org.example.spring.dynamic.datasource.aop;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@MockMergedAnnotation
public @interface MockMergedAnnotationChild {
}
