package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.June;

import java.util.HashMap;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:06
 * @Description:    魔法阵的能量收集-200分 最佳任务统筹回溯
 *
 */
public class magicCircleEnergyCollection {
    static void main() {
        int[] nums= {1,2,3,4,5};
        magicCircleEnergyCollection mce = new magicCircleEnergyCollection();
        int n = 5;
        int k = 3;
        int solve = mce.solve(nums, n, k);
        System.out.println(solve);
    }

    public int solve(int[] nums, int n, int k) {
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        long total = prefix[n];
        long total_r = (total % k + k) % k;

        long ans1 = Long.MIN_VALUE;
        HashMap<Integer, Long> minMap = new HashMap<>();
        minMap.put(0, 0L);
        for (int j = 1; j <= n; j++) {
            int r = (int) ((prefix[j] % k + k) % k);
            if (minMap.containsKey(r)) {
                long candidate = prefix[j] - minMap.get(r);
                if (candidate > ans1) ans1 = candidate;
            }
            if (!minMap.containsKey(r) || prefix[j] < minMap.get(r)) {
                minMap.put(r, prefix[j]);
            }
        }

        long minS = Long.MAX_VALUE;
        HashMap<Integer, Long> maxMap = new HashMap<>();
        for (int j = 1; j <= n; j++) {
            int r_j = (int) ((prefix[j] % k + k) % k);
            int r_i = (r_j - (int) total_r + k) % k;
            if (maxMap.containsKey(r_i)) {
                long candidate = prefix[j] - maxMap.get(r_i);
                if (candidate < minS) minS = candidate;
            }
            if (!maxMap.containsKey(r_j) || prefix[j] > maxMap.get(r_j)) {
                maxMap.put(r_j, prefix[j]);
            }
        }

        long ans = ans1;
        if (minS != Long.MAX_VALUE) {
            long ans2 = total - minS;
            if (ans2 > ans) ans = ans2;
        }
        return (ans == Long.MIN_VALUE) ? 0 : (int) ans;
    }

}
