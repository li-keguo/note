package com.example.springaopdemo.client;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * NameServiceF
 */
public interface NameServiceF {
    /**
     * getName
     *
     * @return name
     */
    @RequestMapping("/")
    String getName();
    
    /**
     * haha
     *
     * @return string
     */
    @RequestMapping("/haha")
    String haha();
}
