package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.DP.hard;

/**
 * @Author: xhl
 * @Date: 2026-07-14 05:21
 * @Description: 115. 不同的子序列
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 */
public class numDistinct {
    static void main() {
        String s = "rabbbit";
        String t = "rabbit";

        numDistinct nd = new numDistinct();
        int i = nd.numDistinct(s, t);
        System.out.println("s 的 子序列 中 t 出现的个数 :" + i);
    }
    // 方法一：动态规划
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m<n) return 0;
        //dp[i][j] 表示在 s[i] 的子序列中 t[j] 出现的个数。
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}
