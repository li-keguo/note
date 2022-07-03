package com.spring.exercise.springinjectdemo;

import com.spring.exercise.springinjectdemo.common.AssembledBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static com.spring.exercise.springinjectdemo.util.DemoUtil.show;

/**
 * SpringInjectDemoApplication
 */
@RequiredArgsConstructor
@SpringBootApplication
public class SpringInjectDemoApplication implements ApplicationRunner {
    
    private final Map<String, AssembledBean> assembledBeans;
    
    private final ApplicationContext applicationContext;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringInjectDemoApplication.class, args);
    }
    
    @Override
    public void run(ApplicationArguments args) {
        System.out.println("----------- 以下是自动依赖注入 -----------");
        show(assembledBeans);
        System.out.println("----------- 以下是主动依赖查找 -----------");
        show(applicationContext.getBeansOfType(AssembledBean.class));
    }
    
}
