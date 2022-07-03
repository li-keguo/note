package com.spring.exercise.springinjectdemo.factory;

import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.Data;

/**
 * FactoryBeanInjectBean
 */
@Data
public class FactoryBeanInjectBean implements AssembledBean {

    private String information = "@default";

    @Override
    public void show() {
        System.out.println("我是通过 调用FactoryBean的方式装配的 FactoryBeanInjectBean " + information);
    }
}
