package com.spring.exercise.springinjectdemo;

import com.spring.exercise.springinjectdemo.common.AssembledBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.spring.exercise.springinjectdemo.util.DemoUtil.show;

/**
 * @author likeguo
 */
public class XmlInjectDemo {

    public static void main(String[] args) {
        final String configLocation = "MATE-INFO/xml/xml-assembled-config.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        show(context.getBeansOfType(AssembledBean.class));
    }
}
