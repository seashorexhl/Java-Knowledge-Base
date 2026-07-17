package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-07-06 08:46
 * @Description: 219. 存在重复元素 II
 */
public class containsNearbyDuplicate {
    static void main() {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        containsNearbyDuplicate cnd = new containsNearbyDuplicate();
        boolean b = cnd.containsNearbyDuplicate(nums, k);
        System.out.println("是否存在重复元素？" + b);
    }

    // 不考虑 K 的情况下
    public boolean containsNearbyDuplicate(int[] nums ) {
        /*Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        boolean b ;
        if (set.size() == k) {
            b = false;
        }else  {
            b= true;
        }
        return b;*/
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 如果 add 返回 false，说明这个元素之前已经存在，即发现了重复元素
            if (!set.add(num)) {
                return true;
            }
        }
        // 循环结束都没有返回 true，说明没有重复元素
        return false;
    }
    /**
     * 方法一：哈希表
     * */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
    /**
     * 方法二：滑动窗口
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

}
