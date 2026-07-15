package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-01 22:22
 * @Description: 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class climbStairs {
    static void main(String[] args) {
        int n = 7;
        climbStairs cs = new climbStairs();
        int i = cs.climbStairs(n);
        System.out.println("有"+ i +"种不同的方法可以爬到楼顶");
    }

    //方法一：动态规划
    public int climbStairs(int n) {
        if(n<=1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
    // 滚动数组
    public int climbStairs2(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
