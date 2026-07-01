package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.DP;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-11 22:02
 * @Description: 爬楼梯
 */
public class ClimbStairsDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClimbStairsDP ssdp = new ClimbStairsDP();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println("方法一、多少种不同的方法可以爬到楼顶:"+ssdp.climbStairs(n));
            System.out.println("方法二、多少种不同的方法可以爬到楼顶:"+ssdp.climbStairs1(n));
            System.out.println("方法三、多少种不同的方法可以爬到楼顶:"+ssdp.climbStairs2(n));
        }

    }
    // 使用循环自底向上计算
    public int climbStairs(int n) {
        // 处理 n=0 或 n=1 的边界情况
        if (n <= 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    /*优化后算法*/
    public int climbStairs1(int n) {
        // 处理 n=0 或 n=1 的边界情况
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        // 使用循环自底向上计算
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n]; // 返回 dp[n]
    }
    /*滚动数组*/
    public int climbStairs2(int n) {
        if (n <= 1) return 1;

        int prev2 = 1; // 代表 dp[i-2]
        int prev1 = 1; // 代表 dp[i-1]
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
