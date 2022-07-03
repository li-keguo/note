package com.example.springaopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author keguoli
 */
@EnableFeignClients
@SpringBootApplication
public class SpringAopDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAopDemoApplication.class, args);
    }


}
