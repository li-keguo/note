package com.example.springaopdemo.client;

import com.example.springaopdemo.annotaion.AspectBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NameService2
 */
@AspectBean
@FeignClient(value = "nameService2", url = "https://locahost:8080")
public interface NameService2 extends NameServiceF {
    /**
     * name
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
