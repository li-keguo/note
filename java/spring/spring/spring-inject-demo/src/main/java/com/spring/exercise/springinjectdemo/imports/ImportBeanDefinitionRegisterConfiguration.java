package com.spring.exercise.springinjectdemo.imports;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author likeguo
 */
@Import({MyImportBeanDefinitionRegistrar.class,
        MyDeferredImportSelector.class,
        ImportBeanDefinitionApiRegisterBean.class
})

@Configuration
public class ImportBeanDefinitionRegisterConfiguration {
}
