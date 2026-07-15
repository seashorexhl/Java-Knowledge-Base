package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: xhl
 * @Date: 2026-07-08 08:40
 * @Description:    3364. 最小正和子数组
 * 给你一个整数数组 nums 和 两个 整数 l 和 r。你的任务是找到一个长度在
 * l 和 r 之间（包含）且和大于 0 的 子数组 的 最小 和。
 * 返回满足条件的子数组的 最小 和。如果不存在这样的子数组，则返回 -1。
 * 子数组 是数组中的一个连续 非空 元素序列。
 */
public class minimumSumSubarray {
    static void main() {
        List<Integer> nums  = new ArrayList<>();
        nums.add(3);
        nums.add(-2);
        nums.add(1);
        nums.add(4);
        int l = 2;
        int r = 3;

        minimumSumSubarray ms = new minimumSumSubarray();
        int i = ms.minimumSumSubarray(nums, l, r);
        System.out.println("Minimum Sum Subarray is: " + i);
    }
    //    方法一：暴力枚举
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        Integer[] a = nums.toArray(Integer[]::new);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= a.length - l; i++) {
            int s = 0;
            for (int j = i; j < a.length && j - i + 1 <= r; j++) {
                s += a[j];
                if (s > 0 && j - i + 1 >= l) {
                    ans = Math.min(ans, s);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    //    方法二：前缀和+定长滑窗+有序集合
    public int minimumSumSubarray1(List<Integer> nums, int l, int r) {
        Integer[] a = nums.toArray(Integer[]::new);
        int ans = Integer.MAX_VALUE;
        int n = a.length;
        int[] s = new int[n + 1];
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int j = 1; j <= n; j++) {
            s[j] = s[j - 1] + a[j - 1];
            if (j < l) {
                continue;
            }
            cnt.merge(s[j - l], 1, Integer::sum); // cnt[s[j-l]]++
            Integer lower = cnt.lowerKey(s[j]);
            if (lower != null) {
                ans = Math.min(ans, s[j] - lower);
            }
            if (j >= r) {
                int v = s[j - r];
                int c = cnt.get(v);
                if (c == 1) {
                    cnt.remove(v);
                } else {
                    cnt.put(v, c - 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

}
