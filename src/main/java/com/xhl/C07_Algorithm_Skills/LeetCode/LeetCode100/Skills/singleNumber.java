package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.Skills;

/**
 * @Author: xhl
 * @Date: 2026-04-22 15:40
 * @Description: 136.只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class singleNumber {
    public static void main(String[] args) {
       int[] nums=new int[]{4,1,2,1,2};
        singleNumber sn =  new singleNumber();
        int x = sn.singleNumber(nums);
        System.out.println(x);
    }
    // 方法一： 位运算
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
