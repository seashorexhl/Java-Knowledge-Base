package com.xhl.C07_Algorithm_Skills.LeetCode.Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-04-09 22:05
 * @Description:  两数之和
 *  思路及算法
 *  注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，
 *  我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
 * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
 * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，
 * 然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
 */
public class twoSum {
    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target = 9;
        twoSum ts = new twoSum();
        int[] ints = ts.twoSum(nums1, target);
        System.out.println(Arrays.toString(ints));
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }
}
