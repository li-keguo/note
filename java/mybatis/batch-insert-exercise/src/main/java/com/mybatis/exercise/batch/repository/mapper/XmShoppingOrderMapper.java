package com.mybatis.exercise.batch.repository.mapper;

import com.mybatis.exercise.batch.model.po.XmShoppingOrder;
import com.mybatis.exercise.batch.support.NewMySqlMapper;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Mapper;

/**
 * XmShoppingOrderMapper
 */
@Mapper
public interface XmShoppingOrderMapper extends NewMySqlMapper<XmShoppingOrder> {

    @Flush
    void flush();
}
