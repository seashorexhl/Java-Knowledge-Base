package com.xhl.C10_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:31
 * @Description:  01 高效的任务规划 Efficient scheduling
 *  贪心策略 + 流水线/前缀和模拟
 */
public class efficientScheduling {
    // 输入获取
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int[][][] tasks = new int[m][][];

        for (int i = 0; i < m; i++) {
            int n = sc.nextInt();
            int[][] task = new int[n][2];
            for (int j = 0; j < n; j++) {
                task[j][0] = sc.nextInt();
                task[j][1] = sc.nextInt();
            }
            tasks[i] = task;
        }

        getResult(tasks);
    }

    /**
     *  贪心策略
     * */
    public static void getResult(int[][][] tasks) {
        for (int[][] task : tasks) {
            // 将每个任务中的机器工作顺序，按照运行时间降序排序
            Arrays.sort(task, (a, b) -> b[1] - a[1]);

            int config_endTime = 0;
            int ans = 0;
            for (int[] info : task) {
                int config_cost = info[0];
                int run_cost = info[1];

                config_endTime += config_cost;
                ans = Math.max(ans, config_endTime + run_cost);
            }

            System.out.println(ans);
        }
    }

}
