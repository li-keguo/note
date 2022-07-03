package com.spring.exercise.springinjectdemo.xml;

import com.spring.exercise.springinjectdemo.common.AssembledBean;

/**
 * xml 无属性的 bean
 */
public class XmlNoPropertiesBean implements AssembledBean {
    @Override
    public void show() {
        System.out.println("我是通过 xml配置方式装配的 XmlNoPropertiesBean");
    }
}
