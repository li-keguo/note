package com.example.springaopdemo.statter;

import com.example.springaopdemo.client.NameService;
import com.example.springaopdemo.client.NameService2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * FeignAspectRunner
 */
@Component
@ConditionalOnProperty(value = "demo.enable.feign")
@RequiredArgsConstructor
@Slf4j
public class FeignAspectRunner implements ApplicationRunner {
    
    private final NameService nameService;
    
    private final NameService2 nameService2;
    
    @Override
    public void run(ApplicationArguments args) {
        noException(nameService::haha);
        noException(nameService2::getName);
        noException(nameService2::haha);
    }
    
    private void noException(F f) {
        try {
            f.run();
        } catch (Exception e) {
            log.warn("[{}]:{}", e.getClass().getName(), e.getMessage());
        }
    }
    
    interface F {
        void run();
    }
}
