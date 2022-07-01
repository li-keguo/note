package com.mybatis.exercise.batch;

import com.mybatis.exercise.batch.demo.JdbcBatchInsertDemo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * BatchInsertExerciseApplication
 */
@RequiredArgsConstructor
@SpringBootApplication
@MapperScan("cn.leaf.exercise.repository.mapper")
public class BatchInsertExerciseApplication implements ApplicationRunner {
    
    private final JdbcBatchInsertDemo jdbcTemplateDemo;
    
    public static void main(String[] args) {
        SpringApplication.run(BatchInsertExerciseApplication.class, args);
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        jdbcTemplateDemo.mybatisParallelInsertDemo();
    }
}
