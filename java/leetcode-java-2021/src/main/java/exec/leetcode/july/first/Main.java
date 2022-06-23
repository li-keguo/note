package exec.leetcode.july.first;

/**
 * <h1>#38 外观数列</h1>
 * <a href = 'https://leetcode-cn.com/problems/count-and-say/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().countAndSay(10));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        System.out.println(s);
        int count = 0;
        char t = 'a';
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (t != s.charAt(i)) {
                t = s.charAt(i);
                count = 1;
            } else {
                count++;
            }
            if (i >= s.length() -1 || t != s.charAt(i + 1)) {
                r.append(count);
                r.append(t);
            }
        }
        return r.toString();
    }
}
