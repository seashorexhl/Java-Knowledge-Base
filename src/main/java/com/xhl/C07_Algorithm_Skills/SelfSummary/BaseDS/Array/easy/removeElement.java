package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-11 22:02
 * @Description: 27. 移除元素
 *  方法一：双指针
 *  方法二：优化双指针
 */
public class removeElement {
    static void main(String[] args) {
        int[] nums ={3,2,2,3};
        int val = 3;
        int i1 = removeElement(nums, val);
        for (int i = 0; i <i1 ; i++) {
            System.out.print(nums[i1]+ " ");
        }
    }
    /**
     *  方法一：双指针
     * */
    // 调用者可以直接通过返回的长度截取数组的前 left 个元素作为最终结果。
    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {

            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return  left;
    }
    /**
     *  方法二：双指针优化
     * */
    public  static int removeElement1(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
