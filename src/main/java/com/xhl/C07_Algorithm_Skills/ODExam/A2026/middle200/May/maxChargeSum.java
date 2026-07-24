package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:09
 * @Description: 05.27 充电桩最优布局规划-200分
 *
 */
public class maxChargeSum {

    static void main(String[] args) {
        int n = 5;
        int m = 2;
        int k = 3;
        int[] demands = {10,20,30,40,50};
        maxChargeSum mcs = new maxChargeSum();
        int maxed = mcs.maxChargeSum(n, m, k, demands);
        System.out.println("最优的充电站布局方案 : " + maxed);

    }
    /**
     *  动态规划
     * */
    public int maxChargeSum(int n, int m, int k, int[] demands) {
        if (m == 0) return 0;
        int[] prevMax = new int[n];
        for (int i = 0; i < n; i++) {
            prevMax[i] = (i == 0) ? demands[i] : Math.max(prevMax[i-1], demands[i]);
        }

        for (int j = 2; j <= m; j++) {
            int[] currentDP = new int[n];
            int[] currentMax = new int[n];
            Arrays.fill(currentDP, Integer.MIN_VALUE);
            Arrays.fill(currentMax, Integer.MIN_VALUE);
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    currentDP[i] = prevMax[i - k] + demands[i];
                } else if (j == 1) {
                    currentDP[i] = demands[i];
                }
                currentMax[i] = (i == 0) ? currentDP[i] : Math.max(currentMax[i-1], currentDP[i]);
            }
            prevMax = currentMax;
        }
        return prevMax[n-1];
    }

}
