package exec.leetcode.september.two;


/**
 * <h1>#1140. 石子游戏</h1>
 * <a href = 'https://leetcode-cn.com/problems/stone-game-ii/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        int[] matrix = {1, 2, 3, 4};
        System.out.println(new Main().stoneGameII(matrix));

    }

    public int stoneGameII(int[] piles) {
        int N = piles.length;
        int preSum = 0;
        int[][] f = new int[N][N + 1];
        for (int i = N - 1; i >= 0; i--) {
            preSum += piles[i];
            for (int M = 1; M <= N; M++) {
                if (i + 2 * M >= N) {
                    f[i][M] = preSum;
                } else {
                    for (int x = 1; x <= 2 * M; x++) {
                        f[i][M] = Math.max(f[i][M], preSum - f[i + x][Math.max(M, x)]);
                    }
                }

            }
        }
        return f[0][1];
    }


}
