package exec.leetcode.july.two;


import java.util.Arrays;

/**
 * <h1>#130 被围绕的区域</h1>
 * <a href = 'https://leetcode-cn.com/problems/surrounded-regions/'>题目</a>
 *
 * @author likeguo
 */
public class Main {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new Main().solve(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        // 对两侧两列开始搜索预标记
        for (int i = 0; i < row; i++) {
            searchAndPreMarking(board, i, 0);
            searchAndPreMarking(board, i, col - 1);
        }
        // 对上下两行开始搜索预标记
        for (int j = 0; j < col; j++) {
            searchAndPreMarking(board, 0, j);
            searchAndPreMarking(board, row - 1, j);
        }

        // 遍历表格，被预先标记对为从边界（root）可达，其余为未可达，统一标记为清除
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 搜索预标记
     *
     * @param board    数据
     * @param rowIndex 行索引
     * @param colIndex 列索引
     */
    public void searchAndPreMarking(char[][] board, int rowIndex, int colIndex) {
        // 超过边界，结束搜索标记
        if (rowIndex < 0 || rowIndex >= board.length || colIndex < 0 || colIndex >= board[0].length) {
            return;
        }
        // 如果为X或者被预标记(-),结束搜索
        if (board[rowIndex][colIndex] != 'O') {
            return;
        }
        // 预标记
        board[rowIndex][colIndex] = '-';
        // 搜索
        searchAndPreMarking(board, rowIndex - 1, colIndex);
        searchAndPreMarking(board, rowIndex + 1, colIndex);
        searchAndPreMarking(board, rowIndex, colIndex - 1);
        searchAndPreMarking(board, rowIndex, colIndex + 1);
    }
}
