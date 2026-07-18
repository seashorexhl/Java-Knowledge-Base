package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:06
 * @Description: 最小请求间隔限流策略-100分
 * 动态规划
 *
 */
public class countSchemes {
    static void main(String[] args) {
        int[] timestamps =  new int[3];
        int minInterval = 3;
        int i = countSchemes(timestamps, minInterval);
        System.out.println("一共有多少种合法的放行方案:"+i);
    }
    // 动态规划
    public static int countSchemes(int[] timestamps, int minInterval) {
        Arrays.sort(timestamps);
        int n = timestamps.length;
        int[] f = new int[n];
        int total = 1;
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (timestamps[i] - timestamps[j] >= minInterval) {
                    f[i] += f[j];
                }
            }
            total += f[i];
        }
        return total;
    }
}
