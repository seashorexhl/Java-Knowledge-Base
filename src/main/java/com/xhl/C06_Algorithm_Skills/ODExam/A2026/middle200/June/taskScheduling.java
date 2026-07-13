package com.xhl.C06_Algorithm_Skills.ODExam.A2026.middle200.June;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:12
 * @Description:  0607核心代码编程-最佳任务统筹回溯 -200分
 */
    /**
     * 问题分析
     * 任务调度问题：给定 n 个任务（n ≤ 20），每个任务有持续时间、截止时间和收益。
     * 任务必须顺序执行，不可并行。若任务在截止时间前完成，则获得收益；否则收益为0。
     * 目标：选择最优任务顺序，最大化总收益。
     * 解法：使用状态压缩动态规划（Bitmask DP）。状态 dp[mask] 表示对应任务集合 mask 的最大收益。对于每个状态，添加新任务时检查是否满足截止时间，并更新收益。
     * 时间复杂度：𝑂(2𝑛×𝑛)，其中 𝑛为任务数，2𝑛≈106对于 𝑛=20可接受。
     * */
public class taskScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1. 第一行单独读取 n
        int n = Integer.parseInt(sc.nextLine().trim());

        // 2. 接下来三行，每行读取一个数组
        int[] duration = readArray(sc.nextLine(), n);
        int[] deadline = readArray(sc.nextLine(), n);
        int[] profit = readArray(sc.nextLine(), n);

        // 3. 调用核心算法
        int result = solve(n, duration, deadline, profit);
        System.out.println("获得的最大总收益为：" + result );
        sc.close();
    }
    /**
     * 标准的空格分隔数组读取方法
     */
    private static int[] readArray(String line, int n) {
        String[] parts = line.trim().split("\\s+"); // 按一个或多个空格/Tab分割
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }
    //
    public static int solve(int n, int[] duration, int[] deadline, int[] profit) {
        int stateSize = 1 << n;
        int[] totalDuration = new int[stateSize];
        int[] dp = new int[stateSize];

        // 预计算总持续时间
        for (int mask = 0; mask < stateSize; mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    totalDuration[mask] += duration[i];
                }
            }
        }

        // DP转移
        for (int mask = 0; mask < stateSize; mask++) {
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) continue;
                int newMask = mask | (1 << j);
                int currentTime = totalDuration[mask];
                int completionTime = currentTime + duration[j];
                int addValue = (completionTime <= deadline[j]) ? profit[j] : 0;
                int candidateProfit = dp[mask] + addValue;
                if (candidateProfit > dp[newMask]) {
                    dp[newMask] = candidateProfit;
                }
            }
        }
        return dp[stateSize - 1];
    }

}
