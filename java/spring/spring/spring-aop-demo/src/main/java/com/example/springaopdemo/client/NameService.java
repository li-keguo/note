package com.example.springaopdemo.client;

import com.example.springaopdemo.annotaion.AspectBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NameService
 */
@AspectBean
@FeignClient(value = "name", url = "https://locahost:8080")
public interface NameService {
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
