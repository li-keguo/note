package com.mybatis.exercise.mapper;

import com.mybatis.exercise.mapper.extend.NewMySqlMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用mapper扩展，只支持mysql的语法
 *
 * @param <T> <T>
 */
public interface MySqlMapper<T> extends Mapper<T>, NewMySqlMapper<T> {
}
