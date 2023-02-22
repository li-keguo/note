package cn.leaf.exercise.multi.datasource.repository.mapper;


import cn.leaf.exercise.multi.datasource.model.po.XmShoppingOrder;
import cn.leaf.exercise.multi.datasource.support.mybatis.NewMySqlMapper;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @author likeguo
 */

@Mapper
public interface XmShoppingOrderMapper extends NewMySqlMapper<XmShoppingOrder>, tk.mybatis.mapper.common.Mapper<XmShoppingOrder>, MySqlMapper<XmShoppingOrder> {
    
    
    /**
     * flush
     */
    @Flush
    void flush();
}
