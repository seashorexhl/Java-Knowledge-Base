package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DivideConquer.Kadane;

/**
 * @Author: xhl
 * @Date: 2026-07-02 15:51
 * @Description:   53. 最大子数组和
 *  具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class maxSubArray {
    static void main() {
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray msa = new maxSubArray();
        int maxed = msa.maxSubArray(nums);
        System.out.println(maxed);
    }
    /**
     *  方法一：动态规划
     * */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public int maxSubArray1(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     *  方法二：分治
     * */
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
    /**
     *  Kadane 算法
     * */

}
