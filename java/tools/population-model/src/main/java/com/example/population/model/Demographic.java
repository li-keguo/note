package com.example.population.model;

import java.math.BigDecimal;

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
     * 死亡率 mortality rate
     */
    private BigDecimal mortalityRate;
    
    
    
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
