package com.example.data.export.tools.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Consumer.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class Consumer<T> {
    
    private final int size;
    private List<T> list;
    
    
    public Consumer(int size) {
        this.size = size;
        list = new ArrayList<>(size);
    }
    
    public boolean add(final T t) {
        if (isFull()) {
            return false;
        }
        list.add(t);
        return true;
    }
    
    public List<T> consumer() {
        List<T> rs = list;
        list = new ArrayList<>(size);
        return rs;
    }
    
    public boolean isFull() {
        return list.size() >= size;
    }
}
