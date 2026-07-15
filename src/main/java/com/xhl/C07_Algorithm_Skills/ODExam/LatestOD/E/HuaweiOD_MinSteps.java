package com.xhl.C09_Career_Growth.ODExam.LatestOD.E;

/**
 * @Author: xhl
 * @Date: 2026-06-29 14:43
 * @Description: 正好走到数组最后一个成员，所使用的最少步骤数
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HuaweiOD_MinSteps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取一行输入并按空格分割
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println(-1);
            return;
        }

        String[] parts = line.split("\\s+");
        int len = parts.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        // 如果只有一个元素，已经在终点
        if (len == 1) {
            System.out.println(0);
            return;
        }

        // BFS 队列：存储当前所在的索引
        Queue<Integer> queue = new LinkedList<>();
        // 记录是否访问过，防止重复计算
        boolean[] visited = new boolean[len];

        // 初始化第一步：步长范围是 1 <= step < len / 2
        // 即可以到达的索引范围是 [1, len/2 - 1]
        int maxFirstStep = len / 2;
        for (int step = 1; step < maxFirstStep; step++) {
            int nextIdx = step; // 因为从索引0出发，走step步，到达索引step
            if (nextIdx == len - 1) {
                // 第一步直接到达终点
                System.out.println(1);
                return;
            }
            if (nextIdx < len - 1 && !visited[nextIdx]) {
                queue.offer(nextIdx);
                visited[nextIdx] = true;
            }
        }

        // 如果第一步没有任何合法落点（例如 len=2 时，len/2=1，step<1 无解）
        if (queue.isEmpty()) {
            System.out.println(-1);
            return;
        }

        int steps = 1; // 当前已经走了1步
        boolean found = false;

        // 标准 BFS 模板
        while (!queue.isEmpty()) {
            steps++; // 准备走下一步
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();
                int jump = nums[currIdx]; // 当前元素的值作为步长
                int nextIdx = currIdx + jump;

                if (nextIdx == len - 1) {
                    // 刚好到达最后一个成员
                    System.out.println(steps);
                    found = true;
                    break;
                } else if (nextIdx < len - 1) {
                    // 还在数组中间，且未访问过，则入队
                    if (!visited[nextIdx]) {
                        queue.offer(nextIdx);
                        visited[nextIdx] = true;
                    }
                }
                // 如果 nextIdx > len - 1，说明跳出了数组，直接忽略
            }
            if (found) break;
        }

        // BFS 结束仍未找到
        if (!found) {
            System.out.println(-1);
        }
    }
}