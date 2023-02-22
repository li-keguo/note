package cn.leaf.exercise.multi.datasource.support.interrupter;

import cn.leaf.exercise.multi.datasource.support.annotation.Switch;
import cn.leaf.exercise.multi.datasource.support.datasource.MultiDataSourceRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author likeguo
 */

@Aspect
@Component
@Order(10)
@Slf4j
@RequiredArgsConstructor
public class SwitchAroundInterrupter {
    
    private final MultiDataSourceRouter dataSourceRouter;
    
    @Pointcut("@annotation(cn.leaf.exercise.multi.datasource.support.annotation.Switch)")
    public void target() {
    }
    
    @Around(value = "@within(sh) || " + "@annotation(sh)")
    public Object verifyRoleExecuteCommand(ProceedingJoinPoint pjp, Switch sh) throws Throwable {
        String dataSource = dataSourceRouter.getDataSource();
        log.debug("原始数据源 {}，本次执行使用数据源 {};执行开始", dataSource, sh.dataSources());
        dataSourceRouter.switchDataSource(sh.dataSources());
        Object result = pjp.proceed();
        log.debug("原始数据源 {}，本次执行使用数据源 {};执行完毕，将切回原数据源 {}", dataSource, sh.dataSources(), dataSource);
        dataSourceRouter.switchDataSource(dataSource);
        return result;
    }
}
