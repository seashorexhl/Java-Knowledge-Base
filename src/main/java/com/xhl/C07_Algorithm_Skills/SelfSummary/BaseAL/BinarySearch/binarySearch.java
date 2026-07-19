package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.BinarySearch;

/**
 * @Author: xhl
 * @Date: 2026-06-10 05:57
 * @Description: 二分查找模板
 *
 */
public class binarySearch {
    public static void main(String[] args) {
        int[] nums=new int[10];
        int target=10;
        binarySearch bs = new binarySearch();
        int i = bs.binarySearch(nums, target);
        System.out.println("查找 " + target +"的索引："+i);
    }
    /*标准 二分查找 模板 */
    // 1.精确查找目标值
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) {                // 循环条件为 <=
            // int mid = (left + right) >>> 1;
            int mid = left + (right - left) / 2; // 防止溢出计算中点

            if (nums[mid] == target) {
                return mid;      // 找到目标直接返回
            } else if (nums[mid] < target) {
                left = mid + 1;  // 目标在右半部分
            } else {
                right = mid - 1; // 目标在左半部分
            }
        }
        return -1; // 未找到
    }
    // 2.查找最小满足条件的值
    public int findLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {                 // 循环条件为 <
            int mid = left + (right - left) / 2; // 中点靠左

            if (nums[mid] >= target) {
                right = mid;   // mid 可能是答案，收缩右边界
            } else {
                left = mid + 1; // mid 不满足，排除左半部分
            }
        }
        // 退出循环时 left == right
        // 建议在此处增加校验：if (nums[left] != target) return -1;
        return left;
    }
    // 3.查找最大满足条件的值
    public int findRightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {   // 循环条件为 <
            int mid = left + (right - left + 1) / 2; // ⚠️ 关键：中点靠右，避免死循环

            if (nums[mid] <= target) {
                left = mid;      // mid 满足条件，尝试更大
            } else {
                right = mid - 1; // mid 不满足，排除右半部分
            }
        }
        return left;
    }
}
