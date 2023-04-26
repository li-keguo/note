package cn.leaf.exercise.multi.datasource.starter;

import cn.leaf.exercise.multi.datasource.config.SlaveDataSourceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * SlaveStarter.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Slf4j
@RequiredArgsConstructor
public class SlaveStarter  implements ApplicationRunner {
    
    private final SlaveDataSourceProperties properties;
    
    private final SwitchDataSourceDemo demo;
    
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("master data-source: {}  {}", properties.getMaster().getName(), properties.getMaster().getName());
        properties.getSlaves().forEach((k, v) -> log.debug("slave data-source: {}  {}", k, v.getName()));
        // 使用 @Switch 选择执行数据库
        demo.annotationSwitchSlave1();
//        // 使用代码切换数据源执行
//        demo.codeSwitchSlave1();
//        demo.flush();
    }
}
