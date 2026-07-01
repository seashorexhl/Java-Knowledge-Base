package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.DP.middle;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-07-01 22:49
 * @Description: 322. 零钱兑换
 */
public class coinChange {
    static void main(String[] args) {
        int amount = 11;
        int[] coins = {1,2,5};
        coinChange cc = new coinChange();
        int i = cc.coinChange(coins, amount);
        System.out.println("可以凑成总金额所需的 最少的硬币个数 :" + i);
    }
    /**
     *  方法一：记忆化搜索
    */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
    //    方法二：动态规划 定义 F(i) 为组成金额 i 所需最少的硬币数量  则 F(i) 对应的转移方程应为
    public int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
