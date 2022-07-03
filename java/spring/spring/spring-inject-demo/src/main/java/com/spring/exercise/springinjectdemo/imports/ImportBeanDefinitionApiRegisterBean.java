package com.spring.exercise.springinjectdemo.imports;

import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.Data;

/**
 * ImportBeanDefinitionApiRegisterBean
 */
@Data
public class ImportBeanDefinitionApiRegisterBean implements AssembledBean {
    
    private String information = "@default";
    
    @Override
    public void show() {
        System.out.println("我是通过 调用ImportBeanDefinition api的方式装配的 BeanDefinitionApiRegisterBean " + information);
    }
}
