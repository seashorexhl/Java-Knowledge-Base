package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP;

/**
 * @Author: xhl
 * @Date: 2026-07-18 18:31
 * @Description: 动态规划
 */
public class DynamicPrograming {
    static void main(String[] args) {

    }

    /**
     * 动态规划 基础模板
     */
    public static int[] dp(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < arr.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + arr[i];
        }
        return dp;
    }

    /**
     * 动态规划 模板
     */
    public static int[] dp2(int[] arr) {
        int n = arr.length;
        // 1. 定义状态：dp[i] 通常表示以 i 结尾的某种最优解
        int[] dp = new int[n];

        // 2. 初始化：处理边界情况
        dp[0] = 1;

        // 3. 状态转移：根据题目逻辑，从已知的 dp[j] (j < i) 推导出 dp[i]
        for (int i = 1; i < n; i++) {
            // 核心逻辑，例如：
            // if (满足条件) {
            //     dp[i] = Math.max(dp[i], dp[j] + 1);// 状态转移 函数
            // }
        }
        return dp;
    }

    /**
     * 滚动数组
     */
    public static int solve(int[] arr) {
        int n = arr.length;
        if (n <= 2) return n;
        int prev2 = 1; // dp[i-2]
        int prev1 = 2; // dp[i-1]
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            // 滚动更新
            prev2 = prev1;
            prev1 = current;
        }
        return current;

    }
}
