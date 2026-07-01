package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.DP.easy;

/**
 * @Author: xhl
 * @Date: 2026-04-12 12:10
 * @Description: 爬楼梯问题  统计
 */
public class climbingStairs {

    public static void main(String[] args) {
        climbingStairs cs = new climbingStairs();
        int n = 5;
        System.out.println("爬 " + n + " 阶楼梯的方法数: " + cs.climbStairs(n));
    }

    /**
     * 计算爬楼梯的方法数
     * @param n 楼梯的阶数
     * @return 方法总数
     */
    public int climbStairs(int n) {
        // 处理边界情况
        if (n <= 2) return n;

        // 1. 定义状态数组
        int[] dp = new int[n + 1];

        // 2. 初始化边界
        dp[1] = 1; // 1阶台阶只有1种方法
        dp[2] = 2; // 2阶台阶有2种方法 (1+1, 2)

        // 3. 状态转移 (自底向上填表)
        for (int i = 3; i <= n; i++) {
            // 核心方程：当前阶数的方法数 = 前一阶的方法数 + 前两阶的方法数
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回结果
        return dp[n];
    }

    // 进阶：空间优化版本 (滚动数组)
    // 因为 dp[i] 只依赖前两个状态，不需要保存整个数组
    public int climbStairsOptimized(int n) {
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