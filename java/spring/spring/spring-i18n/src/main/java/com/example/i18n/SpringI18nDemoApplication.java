package com.example.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author keguoli
 */
@EnableFeignClients
@SpringBootApplication
public class SpringI18nDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringI18nDemoApplication.class, args);
    }


}
