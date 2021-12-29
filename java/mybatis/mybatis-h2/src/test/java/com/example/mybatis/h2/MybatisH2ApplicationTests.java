package com.example.mybatis.h2;

import com.example.mybatis.h2.entity.UserRoleDO;
import com.example.mybatis.h2.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = MybatisH2Application.class)
@RequiredArgsConstructor
class MybatisH2ApplicationTests {
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Test
    void selectById() {
        final UserRoleDO userRoleDO = userRoleMapper.selectById("1351007709096976384");
        Assertions.assertNotNull(userRoleDO);
    }
    
    @Test
    void findByUserId() {
        final List<UserRoleDO> userRoleDOS = userRoleMapper.findByUserId("1");
        Assertions.assertNotNull(userRoleDOS);
        Assertions.assertEquals(1, userRoleDOS.size());
    }
}
