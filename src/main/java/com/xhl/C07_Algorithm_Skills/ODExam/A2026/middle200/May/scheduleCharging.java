package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:07
 * @Description: 优化充电桩调度算法-200分
 *  事件驱动模拟 + 优先级队列 + 贪心策略
 *  小顶堆动态维护“最快释放的充电桩”，利用普通队列维护“先来后到的等待者”，在 O(MlogM) 的时间复杂度内完美模拟了整个调度过程
 */
public class scheduleCharging {

    static void main(String[] args) {
        int N = 3;
        int M = 5;
        int[][] chars = {{10,1,0},{10,1,0},{10,1,0},{10,1,0},{10,1,0}};
        int charging = scheduleCharging(N, M, chars);
        System.out.println("最大化完成充电的车辆数量 :" +charging);
    }
    /**
     *  优先级队列 先来先服务 FIFO
     * */
    public static int scheduleCharging(int N, int M, int[][] cars) {
        // 排序后的车辆数组 保证我们能按时间顺序处理到达事件
        Arrays.sort(cars, (a, b) -> Integer.compare(a[0], b[0]));

        int freePiles = N;
        // 小顶堆 记录正在充电的车辆结束时间
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 优先级队列
        //用来记录正在排队等待的车辆（包含到达时间、充电时长、最大等待时间），遵循先来先服务（FIFO）原则
        Queue<int[]> waitQueue = new LinkedList<>();
        int failCount = 0; // 结果
        int i = 0;
        int lastTime = 0;//
        // 事件驱动 主循环
        while (i < M || !heap.isEmpty()) {
            // 获取下一个结束时间
            int tEnd = heap.isEmpty() ? Integer.MAX_VALUE : heap.peek();
            // 获取下一个到达时间
            int tArrive = i < M ? cars[i][0] : Integer.MAX_VALUE;

            if (tEnd <= tArrive) {
                int t = heap.poll();
                freePiles++;
                lastTime = t;
                // 清理超时并分配
                while (!waitQueue.isEmpty()) {
                    int[] car = waitQueue.peek();
                    int at = car[0], wt = car[2];
                    if (t - at > wt) {
                        waitQueue.poll();
                        failCount++;
                    } else {
                        break;
                    }
                }
                if (!waitQueue.isEmpty()) {
                    int[] car = waitQueue.poll();
                    int ct = car[1];
                    heap.add(t + ct);
                    freePiles--;
                }
            } else {
                int t = cars[i][0];
                int at = cars[i][0], ct = cars[i][1], wt = cars[i][2];
                i++;
                lastTime = t;
                while (!heap.isEmpty() && heap.peek() <= t) {
                    // 释放桩，然后检查超时、分配空闲桩...
                    int endTime = heap.poll();
                    freePiles++;
                    while (!waitQueue.isEmpty()) {
                        int[] car = waitQueue.peek();
                        int atW = car[0], wtW = car[2];
                        if (t - atW > wtW) {
                            waitQueue.poll();
                            failCount++;
                        } else {
                            break;
                        }
                    }
                    if (!waitQueue.isEmpty()) {
                        int[] car = waitQueue.poll();
                        int ctW = car[1];
                        heap.add(t + ctW);
                        freePiles--;
                    }
                }
                if (freePiles > 0) {
                    heap.add(t + ct);
                    freePiles--;
                } else {
                    waitQueue.add(new int[]{at, ct, wt});
                }
            }
        }

        while (!waitQueue.isEmpty()) {
            int[] car = waitQueue.poll();
            int at = car[0], wt = car[2];
            if (lastTime - at > wt) {
                failCount++;
            }
        }

        return failCount;
    }

}
