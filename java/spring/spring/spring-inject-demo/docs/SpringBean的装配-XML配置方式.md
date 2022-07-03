## SpringBean的装配-XML配置方式

使用XML配置的方式将Bean交给Spring容器管理，是一种非常古老的事了，但不得不说曾经作为在Spring中主流的设计，支持还是非常到位的，同时他不通过改代码的方式实现Bean的替换，在有些场景下还是非常实用的

关于XML配置的内容很多，这里不重点讨论，此处指关注将Bean通过xml的配置交给Spring容器管理的简单实例

#### 两种加载Xml方式

#### 原始Spring方式

先初始化需要的ApplicatinContext,通过对ApplicationContext依赖查找获取配置的bean，相关的依赖注入都可以通过xml配置的方式进行

```java
    public static void main(String[] args) {
        final String configLocation = "MATE-INFO/xml/xml-assembled-config.xml"; 
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        show(context.getBeansOfType(AssembledBean.class));
    }
```

#### Springboot场景下

Springboot大量使用注解，默认基本都用注解方式驱动，但是使用XMl配置也是可以的，可以通过Configuration机制配合资源管理导入需要的xml，也可以通过在Springboot的启动类上引入相关的配置。为了明确语义，OO原则，最好还是单独使用一个配置类来明确这是个导入某某配置的类

```java
@ImportResource(locations = {"/MATE-INFO/xml/xml-assembled-config.xml"})
@Configuration
public class XmlResourcesConfiguration {
}
```

xml的类也可以通过注解注入到其他的类作为属性，Springboot中使用xml可以将可能需要后续替换的类配置到xml中（如果部署成本较高，但可以频繁改配置重启的话）

### XML简单配置

#### 简单无参数的Bean配置

首先声明一个无参的类

```java
public class XmlNoPropertiesBean implements AssembledBean {
    @Override
    public void show() {
        System.out.println("我是通过 xml配置方式装配的 XmlNoPropertiesBean");
    }
}
```

在xml中配合进入到bean容器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--  装配无属性的 bean  -->
    <bean class="com.spring.exercise.springinjectdemo.xml.XmlNoPropertiesBean"/>
</beans>
```

#### 有参数的Bean配置

此处直接使用构造器的复杂属性注入

首先还是先声明一个需要注入类

```java
@RequiredArgsConstructor
public class XmlConstructPropertiesBean implements AssembledBean {
    private final XmlBeanProperties properties;
    @Override
    public void show() {
        System.out.println("我是通过 xml配置方式装配的 XmlConstructPropertiesBean " 
                           + properties);
    }
}
```

在xml中配合进入到bean容器，首先得先配置属性的bean，如果是简单属性，可直接配置其值，相关语法参考SpEl（Spring Expression language）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--  声明一个 xml bean  的属性  -->
    <bean id="com.spring.exercise.springinjectdemo.common.XmlBeanProperties"
          class="com.spring.exercise.springinjectdemo.common.XmlBeanProperties" 
          init-method="initialize"/>
    <!--  装配一个有属性的 xml bean  -->
    <bean class="com.spring.exercise.springinjectdemo.xml.XmlConstructPropertiesBean">
        <!--  通过构造器注入属性  -->
        <constructor-arg name="properties" 
                       ref="com.spring.exercise.springinjectdemo.common.XmlBeanProperties"/>
    </bean>
</beans>
```



### 附：Spring5.1.3关与xml文档

https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-factory-xml-import