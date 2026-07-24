package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.BinarySearch.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 15:57
 * @Description: 162. 寻找峰值
 *  给定一个数组，寻找值严格大于左右相邻值的元素。
 */
public class findPeakElement {
    static void main() {
        int[] nums = {1,2,3,1};
        findPeakElement fpe = new findPeakElement();
        int peakElement = fpe.findPeakElement(nums);
        System.out.println("最大值的下标为: " + peakElement);
    }
    /**
     *  方法一：一次遍历 寻找最大值
     * */
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }
    /**
     *  方法二：迭代爬坡
     * */
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        int idx = (int) (Math.random() * n);

        while (!(compare(nums, idx - 1, idx) < 0 && compare(nums, idx, idx + 1) > 0)) {
            if (compare(nums, idx, idx + 1) < 0) {
                idx += 1;
            } else {
                idx -= 1;
            }
        }

        return idx;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }
    /**
     *  方法三：方法二的二分查找优化
     * */
    public int findPeakElement2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     *   辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
     * */
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get1(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare1(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }

}
