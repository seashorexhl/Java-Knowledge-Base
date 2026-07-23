package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.BinarySearch.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-02 15:55
 * @Description: 35. 搜索插入位置
 */
public class searchInsert {
    static void main() {
        int[] nums = {1,3,5,6};
        int target = 5;
        searchInsert si = new searchInsert();
        int index = si.searchInsert(nums, target);
        System.out.println(index);
    }
    /**
     *  方法一：二分查找模版
     * */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] < target) {
                left = mid + 1;  // 目标在右半部分
            } else {
                right = mid - 1; // 目标在左半部分
            }
        }
        return -1;
    }
    /**
     * 方法一：二分查找
     * */
    public int searchInsert1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
