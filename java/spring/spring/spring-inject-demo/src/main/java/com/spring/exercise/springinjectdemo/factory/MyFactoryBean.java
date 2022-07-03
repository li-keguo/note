package com.spring.exercise.springinjectdemo.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * MyFactoryBean
 */
@Component
public class MyFactoryBean implements FactoryBean<FactoryBeanInjectBean> {
    @Override
    public FactoryBeanInjectBean getObject() throws Exception {
        return new FactoryBeanInjectBean();
    }
    
    @Override
    public Class<?> getObjectType() {
        return FactoryBeanInjectBean.class;
    }
}
