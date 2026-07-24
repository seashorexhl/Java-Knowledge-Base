package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:04
 * @Description: 多模态版本的最优调度-200分
 * 动态规划
 */
public class maxAccuracy {

    static void main() {
        int N = 2; // 查询次数
        int T = 4; // 总时间预算
        int[] accuracy = {80,90,95}; // 模型准确率
        int[] latency = {1,2,3}; // 模型延迟
        int maxed = maxAccuracy(N, T, accuracy, latency);
        System.out.println(maxed);
    }

    public static int maxAccuracy(int N, int T, int[] accuracy, int[] latency) {
        int M = accuracy.length;
        int[][] dp = new int[N + 1][T + 1];
        for (int k = 0; k <= N; k++) {
            for (int t = 0; t <= T; t++) {
                dp[k][t] = -1;
            }
        }
        for (int t = 0; t <= T; t++) {
            dp[0][t] = 0;
        }
        for (int k = 1; k <= N; k++) {
            for (int t = 0; t <= T; t++) {
                int bestVal = -1;
                for (int m = 0; m < M; m++) {
                    int l = latency[m];
                    int a = accuracy[m];
                    if (t >= l) {
                        int prevT = t - l;
                        if (dp[k - 1][prevT] != -1) {
                            int candidate = dp[k - 1][prevT] + a;
                            if (candidate > bestVal) {
                                bestVal = candidate;
                            }
                        }
                    }
                }
                dp[k][t] = bestVal;
            }
        }
        int maxAcc = -1;
        for (int t = 0; t <= T; t++) {
            if (dp[N][t] > maxAcc) {
                maxAcc = dp[N][t];
            }
        }
        return (maxAcc == -1) ? 0 : maxAcc;
    }
}