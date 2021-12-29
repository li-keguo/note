## Mybatis使用H2数据库——本地模式



H2数据库是一款开源的数据库，纯java开发，适用于小型的应用和单元测试、集成测试的场景。

H2 [GitHub](https://github.com/li-keguo/gin-vue-admin)

H2 [官方网站](https://github.com/li-keguo/gin-vue-admin)

### 使用H2简单的demo

#### 创建一个spring-boot项目

构建地址：[spring.io](https://start.spring.io/)

保证项目中存在mybatis依赖和H2数据库依赖

```xml
        <!--mybatis 框架-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>
        <!--H2 数据库-->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

这时候项目应该是无法启动的，需要添加数据库相关的配置

#### 添加配置

主要配置数据源，jdbc驱动连接信息，mybatis相关配置

```yml
spring:
  datasource:
    # 驱动
    driver-class-name: org.h2.Driver
    # h2 内存数据库 内存模式连接配置 库名: mybatis
    url: jdbc:h2:mem:mybatis
  h2:
    # 开启console 访问 默认false
    console:
      enabled: true
      settings:
        # 开启h2 console 跟踪 方便调试  默认 false
        trace: true
        # 允许console 远程访问 默认false
        web-allow-others: true
      # h2 访问路径上下文
      path: /h2-console
  sql:
    init:
      # 初始化数据表 DDL
      schema-locations: classpath:sql/init.sql
      # 初始化数据 DML
      data-locations: classpath:sql/data.sql
# 日志
logging:
  level:
    cn:
      felord: debug

# mybatis 配置
mybatis:
  mapper-locations: classpath:mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true

```

项目中配置了`schema-locations` 和`data-locations`, 因为H2数据库是内存数据库，启动时需要加载数据库元信息，两个配置，前者是数据库表结构文件路径，后者是初始化数据脚本文件路径，故此需要添加两个文件到resouces/sql下

创建文件夹sql,创建init.sql，创建data.sql

Init.sql

```sql
-- ----------------------------
-- Table structure for user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`           varchar(128) NOT NULL COMMENT 'primary key id',
    `user_id`      varchar(128) NOT NULL COMMENT 'user primary key',
    `role_id`      varchar(128) NOT NULL COMMENT 'role primary key',
    `date_created` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    `date_updated` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
    PRIMARY KEY (`id`)
);;

```

data.sql

```sql
/** insert admin role */
INSERT  INTO `user_role` (`id`, `user_id`, `role_id`, `date_created`, `date_updated`)
    VALUES ('1351007709096976384', '1', '1346358560427216896', '2021-01-18 11:25:13', '2021-01-18 11:25:13');

```

这时候项目应该可以正常启动

#### 添加测试代码

首先添加mybatis配置

```java
@MapperScan("com.example.mybatis.h2.mapper")
public class MybatisConfig {
}
```

添加mapper文件

```java
@Mapper
public interface UserRoleMapper {

    /**
     * select role by id.
     *
     * @param id primary key.
     * @return {@linkplain UserRoleDO}
     */
    UserRoleDO selectById(String id);

    /**
     * find by user id.
     *
     * @param userId user id
     * @return {@linkplain List}
     */
    List<UserRoleDO> findByUserId(String userId);
}
```

添加mapper映射到xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mybatis.h2.mapper.UserRoleMapper">
    <resultMap id="BaseResultMap" type="com.example.mybatis.h2.entity.UserRoleDO">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="date_created" jdbcType="TIMESTAMP" 
                property="dateCreated"/>
        <result column="date_updated" jdbcType="TIMESTAMP" 
                property="dateUpdated"/>
        <result column="user_id" jdbcType="VARCHAR" property="userId"/>
        <result column="role_id" jdbcType="VARCHAR" property="roleId"/>
    </resultMap>

    <sql id="Base_Column_List">
        id, date_created, date_updated, user_id, role_id
    </sql>

    <select id="selectById" parameterType="java.lang.String" 
            resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from user_role
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <select id="findByUserId" parameterType="java.lang.String" 
            resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from user_role
        where user_id = #{userId,jdbcType=VARCHAR}
    </select>
  </mapper>
```

编写测试代码

```java
@Slf4j
@Component
@RequiredArgsConstructor
public class StartRunner implements ApplicationRunner {
    
    private final UserRoleMapper userRoleMapper;
    
    @Override
    public void run(ApplicationArguments args) {
        final UserRoleDO userRoleDO = 
          userRoleMapper.selectById("1351007709096976384");
        log.info("user: {}", userRoleDO);
        final List<UserRoleDO> userRoleDOS = userRoleMapper.findByUserId("1");
        log.info("user roles : {}", userRoleDOS);
    }
}
```

启动项目，控制台如下输出：

```bash
 INFO 20246 --- [  restartedMain] c.e.mybatis.h2.starter.StartRunner       : user: UserRoleDO{userId='1', roleId='1346358560427216896'}
 INFO 20246 --- [  restartedMain] c.e.mybatis.h2.starter.StartRunner       : user roles : [UserRoleDO{userId='1', roleId='1346358560427216896'}]
```

#### 单元测试

H2数据库因为是内存数据库，所以可以很方便的使用在单元测试的场景中

```java
@SpringBootTest(classes = MybatisH2Application.class)
@RequiredArgsConstructor
class MybatisH2ApplicationTests {
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Test
    void selectById() {
        final UserRoleDO userRoleDO = 
          userRoleMapper.selectById("1351007709096976384");
        Assertions.assertNotNull(userRoleDO);
    }
    
    @Test
    void findByUserId() {
        final List<UserRoleDO> userRoleDOS = userRoleMapper.findByUserId("1");
        Assertions.assertNotNull(userRoleDOS);
        Assertions.assertEquals(1, userRoleDOS.size());
    }
}
```

