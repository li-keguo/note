## SpringAop切入类注解-注解方式实现

Srping Aop使用了AspectJ实现代理，原生支持AspectJ，Spring 实现Aop时，对AspectJ进行了再次封装，提供相关API实现代理。

Spring Aop ：

---- https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#aop-schema-example

#### 定义注解

此处定义注解，只对类可以标注。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface AspectBeanApi {

}
```

#### 定义切面逻辑

此处将进行环切，此部分可通过实现MethodInterceptor进行拦截。

```java
@Aspect
@Component
public class BeanAdviceApiInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("BeanAdviceApiInterceptor proxy execute "
                           + invocation.getMethod().getName());
        return invocation.proceed();
    }
}
```

#### 定义切入点

不同与注解方式，此处对切入点逻辑需要自己实现

```java
@Component
public class BeanPointcut implements Pointcut {
    @Override
    @NonNull
    public ClassFilter getClassFilter() {
        return clazz -> {
            if (AnnotationUtil.hasAnnotation(clazz, AspectBeanApi.class)) {
                return true;
            }
            for (Class<?> anInterface : clazz.getInterfaces()) {
                if (AnnotationUtil.hasAnnotation(anInterface, AspectBeanApi.class)) {
                    return true;
                }
            }
            return false;
        };
    }

    @Override
    @NonNull
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                return ClassUtil.isPublic(method);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(@NonNull Method method, 
                                   @NonNull Class<?> targetClass, @NonNull Object... args) {
                return false;
            }
        };
    }
}

```

#### 配置切入点解析器

API的方式需要提供PointcutAdvisor来组合切入点逻辑和切入逻辑

```java
@Configuration
public class AopConfig {

    @Bean
    public DefaultPointcutAdvisor beanApiPointAdvisor(BeanPointcut beanPointcut,
                                                      BeanAdviceApiInterceptor interceptor){
        return new DefaultPointcutAdvisor(beanPointcut, interceptor);
    }

}
```

#### 使用定义的注解标注切入的类

对类中的方法切入，将定义的注解标注到类定义

```java
@Component
@AspectBeanApi
public class AnnotationExecuteBeanApi implements Execute {

    @Override
    public void execute() {
        System.out.println("AnnotationExecuteBeanApi executing");
    }
}
```

#### 注入执行

通过注入的方式获取代理对象并执行

```java
@Component
@RequiredArgsConstructor
public class AnnotationAspectRunner implements ApplicationRunner {

    private final AnnotationExecuteBeanApi executeBeanApi;

    @Override
    public void run(ApplicationArguments args) {
        executeBeanApi.execute();
    }
    
}
```

#### 执行结果

可以看到，执行时已经通过了代理逻辑。

```log
BeanAdviceApiInterceptor proxy execute execute
AnnotationExecuteBeanApi executing
```

