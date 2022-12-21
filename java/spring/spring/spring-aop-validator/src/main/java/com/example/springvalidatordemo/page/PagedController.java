
package com.example.springvalidatordemo.page;

import com.example.springvalidatordemo.model.entity.ApiResult;
import com.example.springvalidatordemo.model.entity.PageInfo;
import com.example.springvalidatordemo.model.vo.PageCondition;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * PagedController.
 * @author keguoli
 */
public interface PagedController<V, T> {
    
    /**
     * list - paged query.
     *
     * @param pageCondition page condition
     * @return PageInfo
     */
    @PostMapping("list/search")
    default ApiResult<PageInfo<T>> search(@RequestBody @Validated final PageCondition<V> pageCondition) {
        return ApiResult.ok(pageService().searchByPage(pageCondition));
    }
    

    
    /**
     * page service.
     *
     * @return paged service
     */
    PageService<V, T> pageService();
}
