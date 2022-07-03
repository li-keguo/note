package com.spring.exercise.springinjectdemo.imports;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author likeguo
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,@NonNull BeanNameGenerator importBeanNameGenerator) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(ImportBeanDefinitionApiRegisterBean.class)
                .addPropertyValue("information","@ImportBeanDefinitionRegistrar")
                .getBeanDefinition();
        registry.registerBeanDefinition("importBeanDefinitionRegistrar", beanDefinition);
    }
}
