package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.easy;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-06 22:09
 * @Description: 594. 最长和谐子序列
 */
public class findLHS {
    static void main() {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};

        findLHS fl = new findLHS();
        int lhs = fl.findLHS(nums);
        System.out.println("最长和谐子序列 :" + lhs);
    }

    /**
     * 方法一：枚举
     * */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            while(nums[end]-nums[begin] > 1) {
                begin++;
            }
            if (nums[end]-nums[begin] == 1) {
                res = Math.max(res, end-begin+1);
            }
        }
        return res;
    }
    /**
     * 方法二：哈希表
     * */
    public int findLHS1(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) {
            if (cnt.containsKey(key + 1)) {
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }

}
