package com.spring.exercise.springinjectdemo.xml;


import com.spring.exercise.springinjectdemo.common.AssembledBean;
import com.spring.exercise.springinjectdemo.common.XmlBeanProperties;
import lombok.RequiredArgsConstructor;

/**
 * xml 无属性的 bean
 */
@RequiredArgsConstructor
public class XmlConstructPropertiesBean implements AssembledBean {
    
    private final XmlBeanProperties properties;
    
    @Override
    public void show() {
        System.out.println("我是通过 xml配置方式装配的 XmlConstructPropertiesBean " + properties);
    }
}
