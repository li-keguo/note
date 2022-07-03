## SpringAop切入类注解-注解方式实现

Srping Aop使用了AspectJ实现代理，原生支持AspectJ，我们可以使用AspectJ提供的注解进行切入，也可以通过注解的方式切入，如下将尝试使用Aspect的方式对指定的某注解的类进行切入。

Spring Aspect ：

---- https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#aop

#### 定义注解

此处定义注解，只对类可以标注。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface AspectBean {

}
```

#### 定义切面逻辑

此处将进行环切，并可以通过注入注解的方式获取运行时注解的原信息。

```java
@Aspect
@Component
public class BeanAdvice {

    @Pointcut("@within(com.example.springaopdemo.annotaion.AspectBean)")
    public void pointcut() {
        // default implementation ignored
    }

    @Around("pointcut() && @within(aspectBean)")
    public Object advice(ProceedingJoinPoint joinPoint, AspectBean aspectBean) 
      throws Throwable {
        System.out.println("BeanAdvice proxy execute " 
                           + joinPoint.getSignature().getName());
        System.out.println("annotation " 
                           + aspectBean.getClass().getName());
        return joinPoint.proceed();
    }
}
```

#### 使用定义的注解标注切入的类

对类中的方法切入，将定义的注解标注到类定义

```java
@Component
@AspectBean
public class AnnotationExecuteBean implements Execute {

    @Override
    public void execute() {
        System.out.println("AnnotationExecuteBean executing");
    }
}
```

#### 注入执行

通过注入的方式获取代理对象并执行

```java
@Component
@RequiredArgsConstructor
public class AnnotationAspectRunner implements ApplicationRunner {

    private final AnnotationExecuteBean executeBean;

    @Override
    public void run(ApplicationArguments args) {
        executeBean.execute();
    }
    
}
```

#### 执行结果

可以看到，执行时已经通过了代理逻辑。

```log
BeanAdvice proxy execute execute
annotation com.sun.proxy.$Proxy40
AnnotationExecuteBean executing
```

