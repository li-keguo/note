package exec.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LRUMain
 *
 * @author <a href='mailto:likeguo@mininglamp.com'> likeguo </a>
 */
public class LRUMain {
    
    public static void main(String[] args) {
        final LRUCache cache = new LRUCache(5);
        cache.add("1", "a");
        cache.add("2", "b");
        cache.show();
        
    }
    
    static class SimpleLRUCache {

        private final Map<String, String> cache;
        
        public SimpleLRUCache(final int capacity) {
            cache = new LinkedHashMap<String, String>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }
        
        public String get(final String key) {
            return cache.getOrDefault(key, null);
        }
        
        public void add(final String key, String value) {
            cache.put(key, value);
        }
        
    }
    
    
    static class LRUCache {
        
        private LRUNode head;
        
        private LRUNode last;
        private final Map<String, LRUNode> cache;
        
        private final int capacity;
        
        public LRUCache(final int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(capacity);
        }
        
        public String get(final String key) {
            final LRUNode lru = cache.get(key);
            if (Objects.nonNull(lru)) {
                return lru.getValue();
            }
            return null;
        }
        
        public void add(final String key, String value) {
            final LRUNode lru = cache.get(key);
            if (Objects.nonNull(lru)) {
                lru.setValue(value);
                return;
            }
            if (cache.keySet().size() == capacity) {
                // 删除队尾
                removeLast();
            }
            final LRUNode newNode = new LRUNode(key, value);
            cache.put(key, newNode);
            addHead(newNode);
            addLast(newNode);
        }
        
        public void show() {
            show("last:", last);
//            show("head:", head);
            // hi，这个问题得到解决了吗？如果已经解决，期待你的恢复
        }
        
        private void show(String title, LRUNode node) {
            System.out.print(title);
            while (node != null) {
                System.out.printf(" {%s %s} ", node.key, node.value);
                node = node.next;
            }
            System.out.println();
        }
        
        private void removeLast() {
            final LRUNode removed = cache.remove(last.key);
            if (Objects.equals(removed, head)) {
                head = null;
                last = null;
            } else {
                last = last.previous;
            }
        }
        
        private void addHead(final LRUNode newNode) {
            if (Objects.isNull(head)) {
                // 第一次
                head = newNode;
            } else {
                head.previous = newNode;
                newNode.next = head;
                head = newNode;
            }
            
        }
        
        private void addLast(final LRUNode newNode) {
            if (Objects.isNull(last)) {
                // 第一次
                last = newNode;
            } else {
                newNode.previous = last;
                last.next = newNode;
            }
            
        }
        
    }
    
    static class LRUNode {
        
        private String key;
        
        private String value;
        
        private int usageStatistics;
        
        private long lastAccessTime;
        
        private LRUNode next;
        
        private LRUNode previous;
        
        public LRUNode(final String key, final String value) {
            this.key = key;
            this.value = value;
            usageStatistics = 1;
            lastAccessTime = System.currentTimeMillis();
        }
        
        public String getValue() {
            usageStatistics++;
            lastAccessTime = System.currentTimeMillis();
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        
    }
}
