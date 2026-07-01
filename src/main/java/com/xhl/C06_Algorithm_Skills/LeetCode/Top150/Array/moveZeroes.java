package com.xhl.C06_Algorithm_Skills.LeetCode.Top150.Array;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-04-17 08:23
 * @Description: 移动0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class moveZeroes {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes mz = new moveZeroes();
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    // 交换
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
