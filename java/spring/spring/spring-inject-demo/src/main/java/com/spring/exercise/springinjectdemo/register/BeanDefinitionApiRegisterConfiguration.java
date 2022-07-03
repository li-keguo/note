package com.spring.exercise.springinjectdemo.register;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * 通过{@link BeanDefinition} api 注册到 ioc 容器的方式装配 bean 的配置
 */
@Configuration
@RequiredArgsConstructor
@Order(-100)
public class BeanDefinitionApiRegisterConfiguration {
    
    private final AnnotationConfigApplicationContext beanDefinitionRegistry;
    
    @PostConstruct
    public void configure() {
        // 这种注入方式只能后期通过手动依赖查找来获取了，与其家加载顺序有关
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(BeanDefinitionApiRegisterBean.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("apiRegister", beanDefinition);
        
    }
}
