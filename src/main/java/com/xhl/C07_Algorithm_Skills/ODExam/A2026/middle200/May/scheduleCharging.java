package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:07
 * @Description: 优化充电桩调度算法-200分
 *
 */
public class scheduleCharging {

    static void main(String[] args) {
        int N = 3;
        int M = 5;
        int[][] chars = {{10,1,0},{10,1,0},{10,1,0},{10,1,0},{10,1,0}};
        int charging = scheduleCharging(N, M, chars);
        System.out.println("最大化完成充电的车辆数量 :" +charging);
    }

    public static int scheduleCharging(int N, int M, int[][] cars) {
        Arrays.sort(cars, (a, b) -> Integer.compare(a[0], b[0]));
        int freePiles = N;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Queue<int[]> waitQueue = new LinkedList<>();
        int failCount = 0;
        int i = 0;
        int lastTime = 0;

        while (i < M || !heap.isEmpty()) {
            int tEnd = heap.isEmpty() ? Integer.MAX_VALUE : heap.peek();
            int tArrive = i < M ? cars[i][0] : Integer.MAX_VALUE;

            if (tEnd <= tArrive) {
                int t = heap.poll();
                freePiles++;
                lastTime = t;
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
