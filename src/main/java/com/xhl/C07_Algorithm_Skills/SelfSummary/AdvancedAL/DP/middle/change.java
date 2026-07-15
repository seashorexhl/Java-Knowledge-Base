package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-14 11:41
 * @Description:  518. 零钱兑换 II
 */
public class change {
    static void main(String[] args) {
        int amount = 5;
        int[] coins = {1,2,5};
        change c = new change();
        int change = c.change(amount, coins);
        System.out.println("可以凑成总金额的硬币组合数:" + change);
    }
    // 方法一：动态规划
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        boolean[] valid = new boolean[amount + 1];
        dp[0] = 1;
        valid[0] = true;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                valid[i] |= valid[i - coin];
            }
        }
        if(!valid[amount]) return 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
