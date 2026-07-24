package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:56
 * @Description: 麻将基本胡牌型判断-100分
 *  递归 与 回溯算法 (DFS + Backtracking) + 记忆化搜索 + 贪心
 */
public class countWays {
   /* 算法设计思路：
    统计每种花色点数的牌的数量
    枚举所有可能的将牌（两张相同牌）
    移除将牌后，检查剩余牌是否能组成4个面子
    使用深度优先搜索（DFS）计算每个花色面子的组合方案数
            累加所有有效的胡牌组合数量*/
    /**
     *
     * */
    private static int countWays(int[] arr, int i, Map<String, Integer> memo) {
        if (i > 9) return 1;

        String stateKey = i + ":" + Arrays.toString(arr);
        if (memo.containsKey(stateKey)) return memo.get(stateKey);

        if (arr[i - 1] == 0) {
            int res = countWays(arr, i + 1, memo);
            memo.put(stateKey, res);
            return res;
        }

        int totalWays = 0;
        // 尝试刻子
        if (arr[i - 1] >= 3) {
            arr[i - 1] -= 3;
            totalWays += countWays(arr, i, memo);
            arr[i - 1] += 3;
        }
        // 尝试顺子
        if (i <= 7 && arr[i - 1] >= 1 && arr[i] >= 1 && arr[i + 1] >= 1) {
            arr[i - 1] -= 1;
            arr[i] -= 1;
            arr[i + 1] -= 1;
            totalWays += countWays(arr, i, memo);
            arr[i - 1] += 1;
            arr[i] += 1;
            arr[i + 1] += 1;
        }

        memo.put(stateKey, totalWays);
        return totalWays;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] suits = new int[14];
        int[] points = new int[14];

        for (int i = 0; i < 14; i++) suits[i] = sc.nextInt();
        for (int i = 0; i < 14; i++) points[i] = sc.nextInt();

        int[][] count = new int[4][10];
        for (int i = 0; i < 14; i++) {
            int s = suits[i], p = points[i];
            if (s < 1 || s > 3 || p < 1 || p > 9) {
                System.out.println(0);
                return;
            }
            count[s][p]++;
        }

        int totalAns = 0;
        for (int s = 1; s <= 3; s++) {
            for (int p = 1; p <= 9; p++) {
                if (count[s][p] >= 2) {
                    int[][] newCount = new int[4][10];
                    for (int i = 1; i <= 3; i++) {
                        System.arraycopy(count[i], 0, newCount[i], 0, 10);
                    }
                    newCount[s][p] -= 2;

                    int ways = 1;
                    for (int suit = 1; suit <= 3; suit++) {
                        int[] arr = Arrays.copyOfRange(newCount[suit], 1, 10);
                        Map<String, Integer> memo = new HashMap<>();
                        int w = countWays(arr, 1, memo);
                        ways *= w;
                    }
                    totalAns += ways;
                }
            }
        }

        System.out.println("可以 胡牌的数量为："+totalAns);
    }

}
