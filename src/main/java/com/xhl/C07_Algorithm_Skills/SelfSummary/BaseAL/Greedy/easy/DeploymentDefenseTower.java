package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Greedy.easy;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-26 16:51
 * @Description: DeploymentDefenseTower 星际防御塔部署
 * 从左往右扫，没被覆盖就尽量往右放
 */
public class DeploymentDefenseTower {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = sc.nextInt();
        }

        int count = 0;
        // 记录每个位置是否放置了防御塔
        boolean[] hasTower = new boolean[n];

        for (int i = 0; i < n; i++) {
            // 只关心核心反应堆(1)
            if (nodes[i] == 1) {
                // 检查是否已经被左侧的塔覆盖了（塔覆盖范围是 i-1, i, i+1）
                if (i > 0 && hasTower[i - 1]) {
                    continue;
                }

                // 贪心策略：优先在右侧放置（为了覆盖得更远）
                if (i + 1 < n && nodes[i + 1] == 0) {
                    hasTower[i + 1] = true;
                    count++;
                }
                // 如果右侧不行，尝试在左侧放置
                else if (i > 0 && nodes[i - 1] == 0) {
                    hasTower[i - 1] = true;
                    count++;
                }
                // 左右都不行，无解
                else {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(count);
    }
}
