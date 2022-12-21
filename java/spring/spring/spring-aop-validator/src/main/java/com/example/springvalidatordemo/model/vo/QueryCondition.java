package com.example.springvalidatordemo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * QueryConditon.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {
    
    private String keyword;
    
}
