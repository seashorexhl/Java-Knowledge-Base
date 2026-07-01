package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Greedy.easy;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-26 16:36
 * @Description: 激活符文 ActivateRunes 星际防御塔部署
 * 贪心算法 能右不左，延迟满足 放置最少激活物问题
 */
public class ActivateRunes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] runes = new int[n];
        for (int i = 0; i < n; i++) {
            runes[i] = sc.nextInt();
        }

        int count = 0;
        // 记录每个位置是否放置了水晶
        boolean[] hasCrystal = new boolean[n];

        for (int i = 0; i < n; i++) {
            // 只关心符文的位置
            if (runes[i] == 1) {
                // 检查此符文是否已被其左侧的水晶激活
                if (i > 0 && hasCrystal[i - 1]) {
                    continue;
                }

                // 如果未激活，尝试放置新水晶
                // 贪心策略：优先在右侧放置
                if (i + 1 < n && runes[i + 1] == 0) {
                    hasCrystal[i + 1] = true;
                    count++;
                }
                // 如果右侧不行，尝试在左侧放置
                else if (i > 0 && runes[i - 1] == 0) {
                    hasCrystal[i - 1] = true;
                    count++;
                }
                // 如果左右都无法放置，则无解
                else {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(count);
    }
}
