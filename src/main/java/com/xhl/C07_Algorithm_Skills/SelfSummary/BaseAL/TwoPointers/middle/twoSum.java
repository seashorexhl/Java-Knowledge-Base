package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:10
 * @Description: 167. 两数之和 II - 输入有序数组
 */
public class twoSum {
    static void main() {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        twoSum ts = new twoSum();
        int[] index = ts.twoSum(numbers, target);
        System.out.println(index[0]);
    }

    /**
     * 方法一：二分查找
     *
     */
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
     * 方法二：双指针
     */
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
}
