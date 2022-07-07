package com.example.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MainTest {
    
    
    @Test
    public void demo01() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        
        final Class<?> clazz = new ByteBuddy()
                .subclass(Object.class)
                .name("com.example.HelloWorld")
                .defineMethod("hello", String.class, Modifier.PUBLIC)
                .intercept(FixedValue.value("hello world"))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded();
        final Object o = Arrays.stream(clazz.getConstructors())
                .findFirst()
                .get()
                .newInstance();
        final Method hello = clazz.getMethod("hello");
        System.out.println(hello.invoke(o));
    }
}
