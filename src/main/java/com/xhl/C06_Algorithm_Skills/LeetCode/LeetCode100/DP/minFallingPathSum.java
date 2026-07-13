package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.DP;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-13 18:06
 * @Description: 下降路径最小和
 */
public class minFallingPathSum {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {2,1,3},
                {6,5,4},
                {7,8,9}
        };
        minFallingPathSum obj = new minFallingPathSum();
        System.out.println("下降路径 最小和 为：");
        System.out.println(obj.minFallingPathSum(matrix));
    }
    /**
     *  方法一：动态规划
     * */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mn = dp[i - 1][j];
                if (j > 0) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                }
                dp[i][j] = mn + matrix[i][j];
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

}
