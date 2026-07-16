package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.RegExp;

/**
 * @Author: xhl
 * @Date: 2026-06-14 08:22
 * @Description: LeetCode 3127  构造相同颜色的正方形
 * 枚举与模拟
 */
public class CanMakeSquare {
    public static void main(String[] args) {
        char[][] grid =  {{'B','W','B'},{'B','W','W'},{'B','W','B'}};
        CanMakeSquare obj = new CanMakeSquare();

        System.out.println(obj.canMakeSquare(grid));
    }
    /**
     * 方法一：枚举
     * */
    public boolean canMakeSquare(char[][] grid) {
        // 遍历所有可能的 2x2 正方形的左上角起点
        // 因为题目限制是 3x3 矩阵，所以 i 和 j 最大只能取到 1
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (check(grid, i, j)) {
                    return true;// 只要找到任意一个满足条件的 2x2，直接返回 true
                }
            }
        }
        return false;// 遍历完所有 2x2 都不满足，返回 false
    }
    /*判断是否可以通过修改最多一个格子，使得矩阵中存在一个 2x2 颜色完全相同的正方形*/
    /**
     * 检查以 (x, y) 为左上角的 2x2 矩阵，是否能通过修改最多一个格子变成同色
     * */
    public boolean check(char[][] grid, int x, int y) {
        int count = 0;
        // 遍历 2x2 区域内的 4 个格子

        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                // 关键判断 如果 x和 y
                if (grid[x + i][y + j] == 'B') {
                    count++;
                }
            }
        }
        // 核心逻辑：如果 'B' 的数量不是 2，说明 'B' 有 0, 1, 3 或 4 个
        // 此时必然可以通过修改 <=1 个格子达成同色

        return count != 2;
    }

}
