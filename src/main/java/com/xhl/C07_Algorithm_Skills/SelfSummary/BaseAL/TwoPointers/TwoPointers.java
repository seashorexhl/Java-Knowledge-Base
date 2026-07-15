package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers;

/**
 * @Author: xhl
 * @Date: 2026-06-15 02:48
 * @Description: 2. 左右 指针（对撞指针）
 */
/**
 * 适用场景：有序数组的两数之和、三数之和、数组反转、验证回文串等
 * */
public class TwoPointers {
    static void main() {

    }
    // 有序数组的两数之和（返回索引）
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left, right}; // 返回索引（0-based）
            } else if (sum < target) {
                left++; // 需要更大的值
            } else {
                right--; // 需要更小的值
            }
        }
        return new int[]{-1, -1}; // 无解
    }

    // 验证回文串（忽略非字母数字字符）
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 跳过非字母数字字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;

            if (Character.toLowerCase(s.charAt(left)) !=
                    Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
