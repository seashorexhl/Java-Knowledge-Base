package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:17
 * @Description:    36. 有效的数独
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 */
public class isValidSudoku {
    static void main() {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        isValidSudoku ivs = new isValidSudoku();
        boolean validSudoku = ivs.isValidSudoku(board);
        System.out.println(" 数独是否有效 ? " + validSudoku);
    }
    // 方法一：一次遍历
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
