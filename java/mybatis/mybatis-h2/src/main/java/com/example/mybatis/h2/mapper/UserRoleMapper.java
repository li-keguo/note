package com.example.mybatis.h2.mapper;

import com.example.mybatis.h2.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Set;

/**
 * this is User Role Mapper.
 */
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

    /**
     * insert user role.
     *
     * @param userRoleDO {@linkplain UserRoleDO}
     * @return rows int
     */
    int insert(UserRoleDO userRoleDO);

    /**
     * batch insert userRoles.
     * @param userRoleList the datas to insert
     * @return the count of insert
     */
    int insertBatch(@Param("userRoleList") List<UserRoleDO> userRoleList);

    /**
     * insert selective user role.
     *
     * @param userRoleDO {@linkplain UserRoleDO}
     * @return rows int
     */
    int insertSelective(UserRoleDO userRoleDO);

    /**
     * delete user role.
     *
     * @param id primary key
     * @return rows int
     */
    int delete(String id);

    /**
     * delete role by user id.
     *
     * @param userId user id
     * @return row int
     */
    int deleteByUserId(String userId);

    /**
     * delete role by ids of users.
     * @param userIdSet set of user ids
     * @return the count of deleted
     */
    int deleteByUserIdSet(@Param("userIdSet") Set<String> userIdSet);
}
