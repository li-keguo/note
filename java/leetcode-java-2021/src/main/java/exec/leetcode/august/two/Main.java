package exec.leetcode.august.two;


/**
 * <h1>#240 搜索二维矩阵 II</h1>
 * <a href = 'https://leetcode-cn.com/problems/search-a-2d-matrix-ii/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6},
                {4, 5, 6, 7}
        };
        System.out.println(new Main().searchMatrix(matrix, 4));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length && matrix[row][col] != target) {
            if (matrix[row][col] > target) {
                --row;
            } else {
                ++col;
            }
        }
        return row >= 0 && col < matrix[0].length;
    }

}
