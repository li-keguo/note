package com.example.mybatis.generator;

import lombok.SneakyThrows;
import lombok.val;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator
 */
public class Generator {
    
    @SneakyThrows
    public static void main(String[] args) {
        List<String> objects = new ArrayList<>();
        val configuration = new ConfigurationParser(objects)
                .parseConfiguration(new ClassPathResource("generator/generatorConfig.xml").getFile());
        val generator = new MyBatisGenerator(configuration, new DefaultShellCallback(true), objects);
        generator.generate(null);
    }
}
