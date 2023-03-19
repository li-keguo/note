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
        // 很高兴你对这个议题感兴趣。
        // 为了更好的实现这个议题需要你先对这个项目有所了解，首先需要完成以下几点前提：
        // - 能够正常启动运行Shenyu-Admin
        // - 正常启动Shenyu-Dashboard
        // - 你需要了解Shenyu前后台如何运行的
        // - 在熟悉的过程中重点关注Shenyu-Dashboard如何实现国际化的
        // - 思考如何将前端国际化参数如何传递到后端
        // - 思考如何后端(Shenyu-Admin)如何实现国际化
        // - 注意：我们使用的框架基于JSR303(Hibernation-validate)已经实现了国际化，考虑我们如何将其整合进来
        // 期待你有更多想法和我们交流
        SpringApplication.run(SpringI18nDemoApplication.class, args);
    }
    
    
}
