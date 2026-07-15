package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP;

/**
 * @Author: xhl
 * @Date: 2026-04-12 12:09
 * @Description: 动态规划 算法 模板
 *  1.确定 DP 数组 和 下标的含义
 *  2.确定递推公式
 *  3.初始化 DP 数组
 *  4.确定遍历顺序
 *  5.举例推导 DP 数组
 */
public class DynamicProgrammingTemplate {

    public int solve(int n) {
        // 1. 定义状态 (创建表格/数组)
        // dp[i] 的含义通常是：解决规模为 i 的子问题的最优解
        int[] dp = new int[n + 1];

        // 2. 初始化边界条件 (Base Case)
        // 确定最基础的情况，比如 dp[0] 或 dp[1] 是多少
        dp[0] = 0;
        dp[1] = 1;

        // 3. 状态转移 (填表)
        // 按照顺序，利用状态转移方程计算每一个状态
        for (int i = 2; i <= n; i++) {
            // 核心逻辑：根据之前的状态推导当前状态
            // 例如：dp[i] = Math.min(dp[i-1], dp[i-2]) + cost;
            dp[i] = dp[i - 1] + dp[i - 2]; // 示例：斐波那契逻辑
        }

        // 4. 返回结果
        return dp[n];
    }
}
