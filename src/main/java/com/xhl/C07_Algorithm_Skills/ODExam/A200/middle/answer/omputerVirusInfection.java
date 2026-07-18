package com.xhl.C07_Algorithm_Skills.ODExam.A200.middle.answer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-25 23:19
 * @Description: 01 computer virus infection 电脑病毒感染
 * 图论中的单源最短路径问题   Bellman-Ford 算法
 */
public class omputerVirusInfection {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //局域网内电脑个数
        int M = sc.nextInt(); // M 条网络连接
        int[][] times = new int[M][3]; // 存储每个连接和对应的感染时间

        // i 感染电脑j 需要时间 t
        for (int i = 0; i < M; i++) {
            // 读取每个连接的信息，将电脑编号减1转换为从0开始的索引
            times[i][0] = sc.nextInt() - 1; // 感染源电脑编号
            times[i][1] = sc.nextInt() - 1; // 被感染电脑编号
            times[i][2] = sc.nextInt(); // 感染所需时间
        }
        int VirusNum = sc.nextInt()-1;// 初始被感染的电脑编号，转换为从0开始的索引
        sc.close(); // 关闭输入流
        // 输出感染所有电脑所需的最少时间
        System.out.println(DelayTime(times,N,VirusNum));
    }
    //     计算感染所有电脑所需的最少时间的函数
    public  static  int DelayTime(int[][] times,int N,int K){
        final int INF = Integer.MAX_VALUE / 2; // 定义无穷大的值，用于初始化距离数组
        int[] dist = new int[N]; // 存储从源电脑到其他所有电脑的最短感染时间
        Arrays.fill(dist, INF); // 初始化所有感染时间为无穷大
        dist[K] = 0; // 源电脑的感染时间为0

        // 使用Bellman-Ford算法更新所有电脑的最短感染时间
        for (int i = 0; i < N; i++) {
            for (int[] time : times) {
                int u = time[0], v = time[1], w = time[2];
                // 如果可以通过电脑u感染到电脑v，并且时间更短，则更新电脑v的感染时间
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // 找出所有电脑中最长的感染时间
        int maxWait = 0;
        for (int i = 0; i < N; i++) {
            // 如果有电脑的感染时间仍为无穷大，表示该电脑不可被感染，返回-1
            if (dist[i] == INF) return -1;
            // 更新最长的感染时间
            maxWait = Math.max(maxWait, dist[i]);
        }

        // 返回感染所有电脑所需的最少时间
        return maxWait;
    }

}
