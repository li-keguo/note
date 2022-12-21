package com.example.springvalidatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author keguoli
 */
@EnableFeignClients
@SpringBootApplication
public class SpringValidatorDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringValidatorDemoApplication.class, args);
    }


}
