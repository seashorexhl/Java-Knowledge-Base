package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.DP;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: xhl
 * @Date: 2026-06-08 17:14
 * @Description: 322 .凑零钱问题 暴力穷举法
 *
 */
public class CoinChanges {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        CoinChanges cc = new CoinChanges();
        int i = cc.coinChange1(coins, amount);
        System.out.println(i);

    }
    /**
     *  方法一：记忆化搜索
     * */
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

    /**
     *  方法二:暴力穷举
     * */
    int coinChange1(int[] coins, int amount) {
        // 基础用例
       if(amount == 0) return 0;
       if(amount < 0) return -1;

       int res = Integer.MAX_VALUE;
       for(int coin : coins) {
           // 递归计算出amount - coin 的最少 硬币个数
           int subProblem = coinChange1(coins, amount-coin);

           // 凑出 amount 的最少 硬币个数
           res = Math.min(res, subProblem + 1);
       }
       return res == Integer.MAX_VALUE ? -1 : res;
    }
    /**
     *  方法三：动态规划
     * */
    public int coinChange2(int[] coins, int amount) {
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


    /**
     *  高级写法 完全背包一维优化写法
     * */
     public int coinChange3(int[] coins, int amount) {
         return CompletableFuture.supplyAsync(()->{
             int n = coins.length;
             int[] dp = new int[amount+1];
             Arrays.fill(dp,amount+1);
             dp[0]=0;
             for(int i=1;i<=n;i++){
                 for(int j=coins[i-1];j<=amount;j++){
                     dp[j]=Math.min(dp[j],dp[j-coins[i-1]]+1);
                 }
             }
             return dp[amount] == amount+1 ? -1:dp[amount];
         }).join();
     }
}
