## SpringBean的装配-注解方式

使用注解直接将bean交给Spring容器管理，使用注解的方式是现在spring中最常使用的方式了，简单又好用

主持的注解如下：

```java
@Component
@Service
@Controller
@Repoistry
@Configuration
// 自定义注解：元标注以上任意注解即可作为派生注解使用
```

### 五种常见的装配注解

#### @Component

组件：独立的组件工具，一般是脱离业务逻辑的单独模块，如果有具体的明确的含义，使用@Service或者自定义的注解，使得代码语义明确

```java
@Component
@RequiredArgsConstructor
public class ComponentBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @Component 注解方式装配的 ComponentBean " + properties);
    }
}
```

#### @Service

服务：表示业务逻辑的服务层

```java
@Service
@RequiredArgsConstructor
public class ServiceBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @Service 注解方式装配的 ServiceBean " + properties);
    }
}
```

#### @Controller

控制器：表示网络访问的接口，一般为Http协议访问提供接口，

```java
@Controller
@RequiredArgsConstructor
public class ControllerBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @Controller 注解方式装配的 ControllerBean " + properties);
    }
}
```

#### @Repoistry

仓储：一般为数据访问控制层注解，如果使用MyBatis，类似的有@Mapper

```java
@Repository
@RequiredArgsConstructor
public class RepositoryBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @Repository 注解方式装配的 RepositoryBean " + properties);
    }
}
```

#### @Configuration/@Bean

配置：一般表示一个配置的实例，组合@Bean可以通过配置将需要的Bean装配到容器，被@Configuration的类本身也被装配到Spring容器中了。在xml配置的时代，一个被@Configuration标注的类相当于一个XML配置文件。

```java
@Configuration
@RequiredArgsConstructor
public class ConfigurationBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @Configuration 注解方式装配的 ConfigurationBean " + properties);
    }
    // ------------- ConfigBean 装配
    @Bean
    public ConfigBean configBean(AnnotationBeanProperties properties) {
        return new ConfigBean(properties);
    }
    @RequiredArgsConstructor
    static class ConfigBean implements AssembledBean {
        private final AnnotationBeanProperties properties;
        @Override
        public void show() {
            System.out.println("我是通过 @Bean 注解方式装配的 ConfigBean " + properties);
        }
    }
}
```

### 自定义注解

#### 观察和推断

观察spring中提供的常用的注解：

@Component

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {
	String value() default "";
}
```

@Service

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
	@AliasFor(annotation = Component.class)
	String value() default "";
}
```

@Controller

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
	@AliasFor(annotation = Component.class)
	String value() default "";
}
```

@Repoistry

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {
	@AliasFor(annotation = Component.class)
	String value() default "";
}
```

@Configuration

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
	@AliasFor(annotation = Component.class)
	String value() default "";
	boolean proxyBeanMethods() default true;
}
```

通过观察发现：

@Service、@Controller、@Repoistry、@Configuration都被@Component标注了，类似于一个继承关系。

进一步深入发现：

@Service、@Controller、@Repoistry、@Configuration中都有如下代码：

```java
	@AliasFor(annotation = Component.class)
	String value() default "";
```

于是查看`@AliasFor`发现

`@AliasFor is an annotation that is used to declare aliases for annotation attributes.`

给注解的属性设置别名

既然有这个注解，那么Spring一定在某个地方分析实现了相应的功能，于是继续往下找

```
org.springframework.core.annotation.AnnotationAttributes#findMergedAnnotationAttributes
```

`Find the first annotation of the specified annotationName within the annotation hierarchy above the supplied element and merge that annotation's attributes with matching attributes from annotations in lower levels of the annotation hierarchy.`
`Attributes from lower levels in the annotation hierarchy override attributes of the same name from higher levels, and @AliasFor semantics are fully supported, both within a single annotation and within the annotation hierarchy.`
`In contrast to getAllAnnotationAttributes, the search algorithm used by this method will stop searching the annotation hierarchy once the first annotation of the specified annotationName has been found. As a consequence, additional annotations of the specified annotationName will be ignored.`
`This method follows find semantics as described in the class-level javadoc.`

由此得出：

- Spring注解支持层次结构的
- findMergedAnnotationAttributes会在最高层找寻注解，找到后即可返回

由此我们可以实现自定义的注解，模仿@Service等的实现，实现一个自定义的注解，用于装配Bean

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

@MyComponent
@RequiredArgsConstructor
public class MyComponentBean implements AssembledBean {
    private final AnnotationBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 @MyComponent 注解方式装配的 MyComponentBean "+properties);
    }
}
```

##### 补充-关于注解的组合和继承问题

注解组合：Spring特有实现，通过对新定义的注解标注已有注解，使得定义的注解具备标注的注解的能力，常见有@Component派生系列

注解继承：可以通过 `@Inherited` 元注解实现，底层是用jdk帮我们实现

### 附：Spring5.1.3关于注解的官方文档

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-annotation-config
