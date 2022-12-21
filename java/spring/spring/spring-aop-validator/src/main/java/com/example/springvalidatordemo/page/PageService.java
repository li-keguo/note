
package com.example.springvalidatordemo.page;

import com.example.springvalidatordemo.model.entity.PageInfo;
import com.example.springvalidatordemo.model.vo.PageCondition;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * PageService.
 */
public interface PageService<Q, R> {
    
    
    /**
     * search by page condition.
     *
     * @param pageCondition page condition
     * @return list
     */
    default PageInfo<R> searchByPage(final PageCondition<Q> pageCondition) {
        doConditionPreProcessing(pageCondition.getCondition());
//        PageHelper.startPage(pageCondition.getPageNum(), pageCondition.getPageSize());
        return new PageInfo<>(searchByCondition(pageCondition.getCondition()));
    }
    
    
    
    /**
     * search by condition.
     *
     * @param condition condition
     * @return list
     */
    default List<R> searchByCondition(final Q condition) {
        // default is empty list, if paged used DB query.
        return new ArrayList<>();
    }
    
    /**
     * condition preprocessing.
     *
     * @param condition condition
     */
    default void doConditionPreProcessing(final Q condition) {
        // default is nothing, override condition.
    }
    
}
