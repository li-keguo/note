package exec.leetcode.june.two;

import java.util.Arrays;

/**
 * <h1>#542 01矩阵</h1>
 * <a href = 'https://leetcode-cn.com/problems/01-matrix/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0},
                       {0, 1, 0},
                       {1, 1, 1}};
        int[][] results = new Main().updateMatrix(arr);
        for (int[] result : results) {
            System.out.println(Arrays.toString(result));
        }
    }

    int row = 0;
    int col = 0;

    public int[][] updateMatrix(int[][] matrix) {
        int[][] resultArr = fill(matrix);
        // 水平向左移动 和 竖直向上移动
        leftUp(resultArr);
        // 水平向右移动 和 竖直向下移动
        rightDown(resultArr);
        return resultArr;
    }

    private int[][] fill(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        // 不破坏原来的数据
        int[][] resultArr = new int[row][col];
        // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
        // 如果 (i, j) 的元素为 0，那么距离为 0
        for (int i = 0; i < row; i++) {
            Arrays.fill(resultArr[i], Integer.MAX_VALUE - 1);
            for (int j = 0; j < col; j++) {
                // f(i,j) = 0
                if (matrix[i][j] == 0) {
                    resultArr[i][j] = 0;
                }
            }
        }
        return resultArr;
    }

    /**
     * 水平向左移动 和 竖直向上移动<br>
     * f(i,j) = 1 + min(f(i - 1, j), f(i, j - 1))
     *
     * @param resultArr arr
     */
    private void leftUp(int[][] resultArr) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i - 1 >= 0) {
                    resultArr[i][j] = Math.min(resultArr[i][j], resultArr[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    resultArr[i][j] = Math.min(resultArr[i][j], resultArr[i][j - 1] + 1);
                }
            }
        }
    }

    /**
     * 水平向右移动 和 竖直向下移动 <br>
     * f(i,j) = 1 + min(f(i+1, j), f(i, j + 1))
     *
     * @param resultArr arr
     */
    private void rightDown(int[][] resultArr) {
        for (int i = row - 1; i >= 0; --i) {
            for (int j = col - 1; j >= 0; --j) {
                // f(i,j) = 1 + min(f(i+1, j), f(i, j))
                if (i + 1 < row) {
                    resultArr[i][j] = Math.min(resultArr[i][j], resultArr[i + 1][j] + 1);
                }
                // f(i,j) = 1 + min(f(i, j + 1), f(i, j))
                if (j + 1 < col) {
                    resultArr[i][j] = Math.min(resultArr[i][j], resultArr[i][j + 1] + 1);
                }
            }
        }
    }
}
