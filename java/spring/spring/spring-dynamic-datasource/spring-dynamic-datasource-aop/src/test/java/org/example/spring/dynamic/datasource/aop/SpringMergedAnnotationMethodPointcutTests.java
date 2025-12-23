package org.example.spring.dynamic.datasource.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.example.spring.dynamic.datasource.aop.aop.MergedAnnotationMethodPointcut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * The type Spring merged annotation method pointcut tests.
 */
@SpringBootTest
class SpringMergedAnnotationMethodPointcutTests {


    @Autowired
    private MockBean mockBean;

    /**
     * Test.
     */
    @Test
    public void test() {
        Assertions.assertEquals("mockBean", mockBean.invokeNone());
        Assertions.assertEquals("mockValue", mockBean.invoke());
        Assertions.assertEquals("mockValue", mockBean.invokeChild());
    }

    /**
     * The type Config.
     */
    @Configuration
    @EnableAspectJAutoProxy
    public static class Config {

        /**
         * Default ds advisor default pointcut advisor.
         *
         * @return the default pointcut advisor
         */
        @Bean
        public DefaultPointcutAdvisor defaultDsAdvisor() {
            return new DefaultPointcutAdvisor(new MockPointcut(), (MethodInterceptor) invocation -> "mockValue");
        }

        /**
         * Mock bean mock bean.
         *
         * @return the mock bean
         */
        @Bean
        public MockBean mockBean() {
            return new MockBean("mockBean");
        }
    }


    /**
     * The type Mock pointcut.
     */
    static class MockPointcut extends MergedAnnotationMethodPointcut<MockMergedAnnotation> {

    }


}
