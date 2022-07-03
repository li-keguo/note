package com.spring.exercise.springinjectdemo.imports;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author likeguo
 */
public class MyDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(@NonNull AnnotationMetadata annotationMetadata) {

        return new String[]{ImportBeanDefinitionApiRegisterBean.class.getName()};
    }
}
