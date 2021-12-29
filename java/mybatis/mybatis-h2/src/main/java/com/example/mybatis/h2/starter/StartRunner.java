package com.example.mybatis.h2.starter;

import com.example.mybatis.h2.entity.UserRoleDO;
import com.example.mybatis.h2.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * StartRunner
 *
 * @author likeguo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartRunner implements ApplicationRunner {
    
    private final UserRoleMapper userRoleMapper;
    
    @Override
    public void run(ApplicationArguments args) {
        final UserRoleDO userRoleDO = userRoleMapper.selectById("1351007709096976384");
        log.info("user: {}", userRoleDO);
        final List<UserRoleDO> userRoleDOS = userRoleMapper.findByUserId("1");
        log.info("user roles : {}", userRoleDOS);
    }
}
