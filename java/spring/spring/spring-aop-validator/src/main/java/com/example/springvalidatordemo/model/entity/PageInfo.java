package com.example.springvalidatordemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * PageInfo.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {
    
    private int pageNum;
    
    private int pageSize;
    
    private int size;
    
    private int pages;
    
    protected long total;
    
    protected java.util.List<T> list;
    
    public PageInfo(final List<T> list) {
        this.list = list;
    }
}
