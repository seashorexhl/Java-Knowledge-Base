package com.xhl.Career_Growth.SelfSummary.AdvancedAL.PrefixSum;

/**
 * @Author: xhl
 * @Date: 2026-06-23 15:06
 * @Description: 303. 区域和检索 - 数组不可变
 * 核心思想：在数据初始化时，提前把“从起点到各个位置”的累加结果存下来。当需要查询任意区间时，
 * 通过两次查表和一次减法，直接“切”出目标区间的和。
 */
/**
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和 right 之间的元素的 总和
 * ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 * */
public class NumArray {

    /**
     * 方法一：前缀和
     * */
    int[] sums;

    // 初始化 NumArray
    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i]; // 滚动累加
        }
    }

    //  给定 区 间 范围求和
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }

}
