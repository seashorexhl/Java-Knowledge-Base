package com.xhl.C09_Career_Growth.ODExam.A200.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-17 08:07
 * @Description: 高效的任务规划
 *  你有 n 台机器，编号为 1~n，每台都需要完成一项工作，机器经过配置后都能独立完成一项工作。
 * 假设第 i 台机器你需要花 Bi 分钟进行设置，然后开始运行，Ji 分钟后完成任务。
 * 现在，你需要选择布置工作的顺序，使得用最短的时间完成所有工作。
 * 注意，不能同时对两台进行配置，但配置完成的机器们可以同时执行他们各自的工作。
 */
public class EfficientTaskPlan {
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

    // 算法入口
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
