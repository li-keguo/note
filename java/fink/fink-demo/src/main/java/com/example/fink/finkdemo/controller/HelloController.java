package com.example.fink.finkdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * HelloController.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@RequestMapping("/hello")
@RestController
public class HelloController {
    
    @RequestMapping("say")
    public String say() {
        return "welcome";
    }
    
    @GetMapping(value = "get/{value}")
    public Mono<String> get(@PathVariable("value") String value) {
        return Mono.just("value is " + value);
    }
}
