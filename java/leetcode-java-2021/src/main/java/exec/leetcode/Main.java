package exec.leetcode;

import java.util.Arrays;

/**
 * Main
 *
 * @author <a href='mailto:likeguo@mininglamp.com'> likeguo </a>
 */
public class Main {
    
    
    public static void main(String[] args) {
        final int[] arr = {4, 2, 34, 23, 12, 32, 34, 3, 2};
        show(Arrays.stream(arr.clone()).sorted().toArray());
        sort(arr);
        show(arr);
        
    }
    
    private static void sort(final int[] arr) {
        quick(arr, 0, arr.length - 1);
    }
    
    private static void quick(final int[] arr, final int start, final int end) {
        
        int first = start;
        int last = end;
        int point = arr[first];
        
        while (first != last) {
            while (first < last && arr[last] >= point) {
                last--;
            }
            arr[first] = arr[last];
            while (first < last && arr[last] < point) {
                first++;
            }
            arr[last] = arr[first];
        }
        arr[first] = point;
        if (first > start) {
            quick(arr, start, first);
        }
        if (first + 1 < end) {
            quick(arr, first + 1, end);
        }
    }
    
    
    private static void exchange(final int[] arr, int a, int b) {
        final int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    
    private static void show(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
