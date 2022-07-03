package com.spring.exercise.springinjectdemo.common;

import lombok.Data;

/**
 * xml的方式装配bean的属性
 */
@Data
public class XmlBeanProperties {
    
    private String name;
    
    
    public void initialize() {
        name = "xml default";
    }
    
    @Override
    public String toString() {
        return "properties{" + "name='" + name + '\'' + '}';
    }
}
