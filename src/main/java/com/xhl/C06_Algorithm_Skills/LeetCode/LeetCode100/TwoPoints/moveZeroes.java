package com.xhl.Algorithm_Skills.LeetCode.LeetCode100.TwoPoints;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-04-22 14:52
 * @Description: 移动 0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class moveZeroes {
    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        moveZeroes mz = new moveZeroes();
        mz.moveZeroes(arr);

        System.out.println(Arrays.toString(arr));
    }
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while(right < nums.length){
            if(nums[right] != 0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }

    }
    /*交换*/
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
