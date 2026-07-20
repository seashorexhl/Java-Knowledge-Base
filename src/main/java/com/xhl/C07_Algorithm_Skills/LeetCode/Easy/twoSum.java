package com.xhl.C07_Algorithm_Skills.LeetCode.Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-04-09 22:05
 * @Description:  两数之和
 * 方法一：
 * 方法二：
 * 方法三：
 * 方法四：
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
    /**
     *  方法一：二分查找
     * */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
    /**
     *  方法二：双指针
     * */
    public int[] twoSum1(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
    /**
     *  方法三：哈希表
     * */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }
}
