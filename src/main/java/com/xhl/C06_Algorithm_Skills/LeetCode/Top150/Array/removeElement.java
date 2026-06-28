package com.xhl.Algorithm_Skills.LeetCode.Top150.Array;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-04-17 08:26
 * @Description: 移除元素
 */
public class removeElement {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        removeElement re = new removeElement();
        int res = re.removeElement(nums, val);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }
    // 移位
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
}
