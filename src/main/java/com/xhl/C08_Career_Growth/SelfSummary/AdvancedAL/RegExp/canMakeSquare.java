package com.xhl.Career_Growth.SelfSummary.AdvancedAL.RegExp;

/**
 * @Author: xhl
 * @Date: 2026-06-14 08:22
 * @Description: 构造相同颜色的正方形
 */
public class canMakeSquare {
    public static void main(String[] args) {
        char[][] grid =  {{'B','W','B'},{'B','W','W'},{'B','W','B'}};
        canMakeSquare obj = new canMakeSquare();

        System.out.println(obj.canMakeSquare(grid));
    }
    /* 方法一：枚举 */
    public boolean canMakeSquare(char[][] grid) {
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (check(grid, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    /*判断是否可以通过修改最多一个格子，使得矩阵中存在一个 2x2 颜色完全相同的正方形*/
    public boolean check(char[][] grid, int x, int y) {
        int count = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                // 关键判断 如果 x和 y
                if (grid[x + i][y + j] == 'B') {
                    count++;
                }
            }
        }
        return count != 2;
    }

}
