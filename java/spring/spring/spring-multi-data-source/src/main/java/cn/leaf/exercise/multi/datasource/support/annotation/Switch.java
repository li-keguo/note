package cn.leaf.exercise.multi.datasource.support.annotation;

import java.lang.annotation.*;

/**
 * Switch
 *
 * @author likeguo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Switch {
    
    /**
     * 数据源  默认为 master
     *
     * @return Sting
     */
    String dataSources() default "master";
    
}
