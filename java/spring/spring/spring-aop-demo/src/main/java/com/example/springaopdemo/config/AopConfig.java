package com.example.springaopdemo.config;

import com.example.springaopdemo.advice.BeanAdviceApiInterceptor;
import com.example.springaopdemo.advice.BeanPointcut;
import com.example.springaopdemo.advice.MethodAdviceApiInterceptor;
import com.example.springaopdemo.advice.MethodPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BeanAdviceApiConfig
 *
 * @author likeguo
 */
@Configuration
public class AopConfig {

    @Bean
    public DefaultPointcutAdvisor beanApiPointAdvisor(BeanPointcut beanPointcut, BeanAdviceApiInterceptor beanAdviceApiInterceptor) {
        return new DefaultPointcutAdvisor(beanPointcut, beanAdviceApiInterceptor);
    }

    @Bean
    public DefaultPointcutAdvisor methodApiPointAdvisor(MethodPointcut methodPointcut,MethodAdviceApiInterceptor interceptor) {
        return new DefaultPointcutAdvisor(methodPointcut, interceptor);
    }
}
