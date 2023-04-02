package com.example.fink.finkdemo;

import lombok.Data;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author keguoli
 */
@SpringBootApplication
public class FinkDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FinkDemoApplication.class, args);
    }
    
    
}
