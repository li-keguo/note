package exec.leetcode.august.one;

/**
 * <h1>#122 买卖股票的最佳时机 II</h1>
 * <a href = 'https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        int[] prices = {};
        System.out.println(new Main().maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int account = 0;
        for (int i = 1; i < prices.length; ++i) {
            // 如果今天买入，明天有的赚，那么就买入，并将利润纳入到盈利账户。否则拉倒
            account += Math.max(0, prices[i] - prices[i - 1]);
        }
        return account;
    }
}
