package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.Map.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-18 20:55
 * @Description: 好数对的数目
 *  给你一个整数数组 nums 。
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 * 返回好数对的数目。
 */
public class numIdenticalPairs {
    static void main() {
        int[] nums = {1,2,3,1,1,3};
        numIdenticalPairs nip = new numIdenticalPairs();
        System.out.printf("此数组种 好数对的数目为：" + nip.numIdenticalPairs(nums));
    }
    // 方法二：组合计数 使用 HashMap
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            int v = entry.getValue();
            ans += v * (v - 1) / 2;
        }
        return ans;
    }
    // 方法一：暴力统计
    public int numIdenticalPairs1(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] == nums[j]) {
                    ++ans;
                }
            }
        }
        return ans;
    }


}
