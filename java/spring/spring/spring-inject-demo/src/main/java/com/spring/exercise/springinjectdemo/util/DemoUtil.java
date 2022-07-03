package com.spring.exercise.springinjectdemo.util;

import com.spring.exercise.springinjectdemo.common.AssembledBean;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DemoUtil
 */
public class DemoUtil {
    
    private DemoUtil() {
    }
    
    /**
     * 遍历map，调用{@link AssembledBean#show()}方法，并显示前缀序号
     *
     * @param map map
     */
    public static void show(Map<String, AssembledBean> map) {
        AtomicInteger i = new AtomicInteger(1);
        map.forEach((k, v) -> {
            System.out.print((i.getAndIncrement()) + "\t" + " :\t");
            v.show();
        });
    }
}
