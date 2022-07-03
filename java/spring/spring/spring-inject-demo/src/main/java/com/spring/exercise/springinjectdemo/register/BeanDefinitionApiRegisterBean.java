package com.spring.exercise.springinjectdemo.register;

import com.spring.exercise.springinjectdemo.common.AssembledBean;

/**
 * BeanDefinitionApiRegisterBean
 */
public class BeanDefinitionApiRegisterBean implements AssembledBean {
    
    @Override
    public void show() {
        System.out.println("我是通过 调用RegisterBeanDefinition api的方式装配的 BeanDefinitionApiRegisterBean ");
    }
}
