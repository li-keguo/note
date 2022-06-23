package exec.leetcode.august.three;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>#398 随机数索引</h1>
 * <a href = 'https://leetcode-cn.com/problems/random-pick-index/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution(new int[]{1, 45, 3, 3, 3, 23, 1});
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));

    }

    static class Solution {
        private final int[] nums;
        private final Random random;
        private final Map<Integer, List<Integer>> cache;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
            cache = new HashMap<>();
        }

        public int pick(int target) {
            List<Integer> indexs = cache.get(target);
            if (indexs != null) {
                return indexs.get(random.nextInt(indexs.size()));
            }
            int result = -1;
            for (int i = 0; i < nums.length; i++) {
                // 第一次匹配
                if (nums[i] == target && result == -1) {
                    result = i;
                    continue;
                }
                // 第n次匹配
                if (nums[i] == target) {
                    indexs = cache.get(target);
                    if (indexs == null) {
                        cache.put(target, Stream.of(result, i).collect(Collectors.toList()));
                    } else {
                        indexs.add(i);
                    }
                }
            }
            return cache.get(target) == null ? result : pick(target);
        }
    }

}
