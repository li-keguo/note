package exec.leetcode.july.three;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>#373 查找和最小的K对数字</h1>
 * <a href = 'https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 7, 11};
//        int[] nums2 = new int[]{2, 4, 6};

//        int[] nums1 = new int[]{1, 1, 2};
//        int[] nums2 = new int[]{1, 2, 3};
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3};
        int k = 3;

        List<List<Integer>> res = new Main().kSmallestPairs(nums1, nums2, k);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.num1 + o.num2));
        for (int num1 : nums1) {
            for (int value : nums2) {
                queue.offer(new Node(num1, value));
            }
        }
        Node node;
        for (int i = 0; i < k; i++) {
            node = queue.poll();
            if (node == null) {
                break;
            }
            res.add(Stream.of(node.num1, node.num2).collect(Collectors.toList()));
        }

        return res;
    }

    static class Node {
        int num1;
        int num2;

        public Node(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}
