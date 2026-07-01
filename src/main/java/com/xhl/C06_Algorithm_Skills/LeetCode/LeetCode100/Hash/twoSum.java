package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.Hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-04-22 14:43
 * @Description: 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 */
public class twoSum {
    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target = 9;
        twoSum ts = new twoSum();
        int[] arr = ts.twoSum(nums1, target);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = ts.twoSum1(nums1, target);
        System.out.println("array2!");
        System.out.println(Arrays.toString(arr2));
    }

    //方法一: :
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]), i};
            }
        }
        return new int[0];
    }
    // 方法 二:
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // 1. 先查：检查之前遍历过的元素中是否有需要的补数
            if (map.containsKey(complement)) {
                // 找到答案，直接返回结果，无需继续遍历
                return new int[]{map.get(complement), i};
            }

            // 2. 后存：将当前元素及其索引放入 map，供后续元素查找
            map.put(nums[i], i);
        }

        // 题目保证有且仅有一个解，若运行到这里说明无解，抛出异常或返回 null
        throw new IllegalArgumentException("No two sum solution");
    }
}
