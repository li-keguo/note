## SpringBean的装配-其他配置方式

除了使用注解和API等常规方式，Spring还提供一些其他的方式将Bean交给IOC容器去管理，例如StringBoot提供的starter机制（类似于SPI的方式），容器生命周期中提供的PostProcessor中注册自定义的BeanDefinition等。

- SpringBootStarter机制（Spring SPI）
- BeanDefinitionRegistry注入到容器中
- 使用自定义FactoryBean创建

### SpringBootStarter机制

#### 自定义SpringBootStarter

在SpringBoot中经常用到xxx-spring-boot-starter，引入之后很神奇的发现，容器中多了很多可以直接注入使用的Bean，那么他是如何使用的呢？那些Bean是如何进入我们的IOC容器中的呢？这里做一个简单的示例：

首先自定义一个SpringBootStarter，在该项目中将需要的放入到容器中的Bean通过@Configuration的方式配置，该项目可以没有启动类

```java
@Configuration
public class MockStarterConfiguration {
    @Bean
    public SpringBootStarterBean springBootStarterBean(){
        return new SpringBootStarterBean();
    }
}
```

在项目的classpath:MATE-INF下创建spring.factories文件，即：classpath:MATE-INF/spring.factories。类似于SPI的类文件，不过SpringBoot提供的这种机制只需要一个文件，在这个文件中提供具体暴露的类路径和扫描该类路径的类，此处配置内容如下：

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.spring.exercise.mock.starter.MockStarterConfiguration
```

#### 使用自定义的SpringBootStarter

和普通的Starter一样，引入依赖，在使用处注入即可。

SpringBootStarter机制还提供很多复杂配置,可以通过@Conditional()家族实现复杂配置。此处不做介绍

### BeanDefinitionRegistry注入

#### 实现BeanDefinitionRegistryPostProcessor

```java
@Component
public class RegisterBeanDefinitionRegistryPostProcessor 
  implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(
      BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinition beanDefinition = BeanDefinitionBuilder
          .genericBeanDefinition(BeanDefinitionApiRegisterBean.class)
          .getBeanDefinition();
        beanDefinitionRegistry
          .registerBeanDefinition("beanDefinitionRegistryPostProcessorRegister"
                                                      , beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory 
                                       configurableListableBeanFactory) 
      throws BeansException {

    }
}
```

容器初始化阶段注册需要的Bean，常用与整合Spring框架中，例如RPC的feign，MyBatis中的Mapper注入等

#### 获取ApplicationContext手动注入

```java
@Configuration
@RequiredArgsConstructor
@Order(-100)
public class BeanDefinitionApiRegisterConfiguration {

    private final AnnotationConfigApplicationContext beanDefinitionRegistry;

    @PostConstruct
    public void configure() {
        // 这种注入方式只能后期通过手动依赖查找来获取了，与其加载顺序有关
        BeanDefinition beanDefinition = BeanDefinitionBuilder
          .genericBeanDefinition(BeanDefinitionApiRegisterBean.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("apiRegister", beanDefinition);

    }
}
```

这种方式值得注意，此处注入无法通过注解注入顺序会比较迟，可能导致相关依赖的类无法注入，可通过依赖查找获取。

#### 使用@Import导入

使用@Import导入有三种方式可以选择，分类一下两种方式使用于简单的Bean的创建，一种适用于复杂的Bean的创建，复杂创建原理也是通过手动注入BeanDefinition到BeanDefinitionRegistry

##### 使用@Import直接导入

```java
@Import(ImportBeanDefinitionApiRegisterBean.class)
@Configuration
public class ImportBeanDefinitionRegisterConfiguration {
}
```

##### 使用实现的方式DeferredImportSelector导入

```java
public class MyDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{ImportBeanDefinitionApiRegisterBean.class.getName()};
    }
}
```

```java
@Import(MyDeferredImportSelector.class)
@Configuration
public class ImportBeanDefinitionRegisterConfiguration {
}
```

##### 使用实现的方式ImportBeanDefinitionRegistrar导入

```java
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry, 
                                        BeanNameGenerator importBeanNameGenerator) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder
          .genericBeanDefinition(ImportBeanDefinitionApiRegisterBean.class)
                .addPropertyValue("information","@ImportBeanDefinitionRegistrar")
                .getBeanDefinition();
        registry.registerBeanDefinition("importBeanDefinitionRegistrar", beanDefinition);
    }
}
```

```java
@Import(MyImportBeanDefinitionRegistrar.class)
@Configuration
public class ImportBeanDefinitionRegisterConfiguration {
}
```

### 自定义FactoryBean创建

实现Factory可以实现复杂Bean的创建，不过在SpringBoot场景下，直接使用@Bean效果应该等同，

```java
@Component
public class MyFactoryBean implements FactoryBean<FactoryBeanInjectBean> {
    @Override
    public FactoryBeanInjectBean getObject() throws Exception {
        return new FactoryBeanInjectBean();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanInjectBean.class;
    }
}
```

FactoryBean算是Spring中对底层了，可以说所有的Spring Bean都是通过FactoryBean创建出来的。

### 附：Spring5.1.3文档

bean-definitin

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-definition

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-child-bean-definitions

bean-factory

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-beanfactory

scanning

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-classpath-scanning

factroy-bean

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#databuffers