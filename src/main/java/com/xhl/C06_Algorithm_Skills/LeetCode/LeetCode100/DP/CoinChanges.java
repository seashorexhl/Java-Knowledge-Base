package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.DP;

/**
 * @Author: xhl
 * @Date: 2026-06-08 17:14
 * @Description: 凑零钱问题 暴力穷举法
 */
public class CoinChanges {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 10;
        CoinChanges cc = new CoinChanges();
        int i = cc.coinChange(coins, amount);
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
     *  方法二：动态规划
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
}
