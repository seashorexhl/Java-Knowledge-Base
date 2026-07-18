package com.xhl.C07_Algorithm_Skills.ODExam.A200.middle.answer;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-26 10:45
 * @Description: 06 可以处理的最大任务数
 *  贪心 + 优先队列 + 时间轴模拟
 */
public class MaxTasksSupported {
    // 创建一个List数组，用于存储所有的任务，每个时间点对应一个任务列表
    static List<Task>[] a = new List[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 读取任务的总数

        // 初始化任务列表数组
        for (int i = 0; i < 100001; i++) {
            a[i] = new ArrayList<>();
        }

        // 读取每个任务的开始时间和结束时间，并将其添加到对应的任务列表中
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(); // 任务开始时间
            int y = sc.nextInt(); // 任务结束时间
            a[x].add(new Task(x, y)); // 创建任务并添加到任务列表中
        }


        int ans = 0; // 用于记录能完成的任务数量
        // 创建一个优先队列，根据任务的结束时间进行排序，确保每次都处理结束时间最早的任务
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.endTime));

        // 遍历每个时间点
        for (int i = 0; i < 100001; i++) {
            // 如果优先队列不为空且队列顶部的任务结束时间小于当前时间，则将其移除
            while (!pq.isEmpty() && pq.peek().endTime < i) {
                pq.poll();
            }

            // 如果当前时间点有任务
            if (a[i] != null) {
                // 将当前时间点的所有任务加入优先队列
                for (Task task : a[i]) {
                    pq.add(task);
                }
            }

            // 如果优先队列不为空，则从队列中移除一个任务，并将完成任务的数量加一
            if (!pq.isEmpty()) {
                ans++;
                pq.poll();
            }
        }

        // 输出能完成的任务数量
        System.out.println(ans);
    }

    // 定义一个Task类来存储每个任务的开始时间和结束时间
    static class Task {
        int startTime;  // 任务开始时间
        int endTime;    // 任务结束时间

        // Task类的构造函数，用于初始化任务的开始时间和结束时间
        Task(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
