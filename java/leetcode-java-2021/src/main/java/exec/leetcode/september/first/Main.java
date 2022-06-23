package exec.leetcode.september.first;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>#139 单词拆分</h1>
 * <a href = 'https://leetcode-cn.com/problems/word-break/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().wordBreak("leetcode", Stream.of("leet", "code").collect(Collectors.toList())));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] status = new boolean[s.length() + 1];
        // 默认字符串为空串时在字典中包含
        status[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // 分段标记是否包含状态
            for (int j = 0; j < i; j++) {
                // 当截取词起始位置段为通过， 并且截取词在字典中，标记该段为通过
                if (status[j] && wordDict.contains(s.substring(j, i))) {
                    status[i] = true;
                    break;
                }
            }
        }
        // 返回最长段状态
        return status[s.length()];
    }

}
