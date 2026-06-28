package com.xhl.Algorithm_Skills.LeetCode.Easy;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-04-09 23:54
 * @Description: 移动0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class moveZeroes {
    public static void main(String[] args) {
        int[] nums1 = new int[] {0,1,0,3,12};
        moveZeroes mz = new moveZeroes();
        mz.moveZero(nums1);
        System.out.println(Arrays.toString(nums1));

    }
    public void moveZero(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
