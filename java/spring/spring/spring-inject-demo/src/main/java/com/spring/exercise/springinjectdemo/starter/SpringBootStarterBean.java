package com.spring.exercise.springinjectdemo.starter;

import com.spring.exercise.springinjectdemo.common.AssembledBean;

/**
 * SpringBootStarterBean
 */
public class SpringBootStarterBean implements AssembledBean {
    
    @Override
    public void show() {
        System.out.println("我是通过 调用SpringBootStarter机制的方式装配的 SpringBootStarterBean ");
        
    }
}
