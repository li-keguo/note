package com.example.population.model;

/**
 * Demographic.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class Demographic {
    
    /**
     * 数量
     */
    private int num;
    
    /**
     * 年龄
     */
    private int age;
    
    /**
     * 性别
     */
    private Sex sex;
    
    /**
     * 比率
     */
    private Rate rate;
    
    
    public static enum Sex {
        /**
         * 男
         */
        MAN,
        /**
         * 女
         */
        WOMAN
    }
}
