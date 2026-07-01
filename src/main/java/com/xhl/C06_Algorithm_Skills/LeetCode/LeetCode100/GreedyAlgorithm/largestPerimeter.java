package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.GreedyAlgorithm;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-16 05:16
 * @Description: LeetCode 976 三角形的最大周长
 * 给定由一些正数（代表长度）组成的数组 nums ，返回 由其中三个长度组成的、面积不为零的三角形的
 * 最大周长 。如果不能形成任何面积不为零的三角形，返回 0。
 */
public class largestPerimeter {
    static void main() {
        int[] nums = {1,2,1,10,6,9,3};
        largestPerimeter lp  = new largestPerimeter();
        System.out.print("三角形的 最大周长：");
        System.out.println(lp.largestPerimeter(nums));
    }
    /**
     *  方法一：贪心 + 排序
     * */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; --i) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }
}
