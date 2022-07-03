package com.spring.exercise.mock.starter;

import com.spring.exercise.springinjectdemo.starter.SpringBootStarterBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author likeguo
 */
@Configuration
public class MockStarterConfiguration {

    @Bean
    public SpringBootStarterBean springBootStarterBean(){
        return new SpringBootStarterBean();
    }
}
