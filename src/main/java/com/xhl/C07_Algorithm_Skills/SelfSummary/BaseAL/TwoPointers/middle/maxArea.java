package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:11
 * @Description:  11. 盛最多水的容器 ⭐⭐⭐
 * 方法一：双指针
 */
public class maxArea {
    static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        maxArea ma = new maxArea();
        int area = ma.maxArea(height);
        System.out.println("容器可以储存的最大水量:" + area);
    }
    /**
     *  方法一：双指针
     * */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
}
