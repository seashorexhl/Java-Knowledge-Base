package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-14 06:01
 * @Description:    354. 俄罗斯套娃信封问题
 * 核心思想是将二维的嵌套问题，通过巧妙的排序，降维转化为一维的最长递增子序列（LIS）问题
 */
public class maxEnvelopes {
    static void main(String[] args) {
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        maxEnvelopes me =   new maxEnvelopes();
        int maxed = me.maxEnvelopes(envelopes);
        int maxed1 = maxEnvelopes1(envelopes);

        System.out.println("最多能有多少个 信封能组成一组“俄罗斯套娃”信封?" + maxed);
        System.out.println("方法二：最多能有多少个 信封能组成一组“俄罗斯套娃”信封"+maxed1);

    }

    /**
     *  方法二：基于二分查找的动态规划
     * */
    public static int maxEnvelopes1(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    // 二分法
    public static int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     *  方法一：动态规划
     *  时间复杂度：O(N^2)，空间复杂度：O(N)
     * */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        // 1. 排序：宽度升序，若宽度相同则高度降序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];// 宽度升序
                } else {
                    return e2[1] - e1[1];// 宽度相同时，高度降序（防止同宽度的信封被错误嵌套）
                }
            }
        });
        // 2. 动态规划求最长递增子序列 (LIS)
        // f[i] 表示以第 i 个信封为最外层时，最多能嵌套的信封数量

        int[] f = new int[n];
        Arrays.fill(f, 1);// 初始状态：每个信封至少可以单独作为一个套娃
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                // 因为已经按宽度排过序，这里只需判断高度是否严格递增
                // (宽度相同的情况已在排序时通过高度降序规避了)
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);// 更新全局最大值
        }
        return ans;
    }

}
