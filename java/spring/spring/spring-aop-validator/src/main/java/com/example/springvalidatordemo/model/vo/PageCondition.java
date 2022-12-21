
package com.example.springvalidatordemo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * page condition.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageCondition<T> {
    
    /**
     * current page num.
     */
    @NotNull
    private Integer pageNum;
    
    /**
     * page size.
     */
    @NotNull
    @Max(value = 1000, message = "size max support is 1000")
    @Min(value = 1, message = "size min support is 1")
    private Integer pageSize;
    
    /**
     * condition.
     */
    @Valid
    @NotNull
    private T condition;
    
}
