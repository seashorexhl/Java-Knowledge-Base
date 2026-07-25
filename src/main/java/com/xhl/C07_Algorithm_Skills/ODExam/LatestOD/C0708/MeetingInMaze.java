package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.C0708;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-19 16:38
 * @Description: Meeting in the Maze 迷宫相遇
 * 邻接表 + BFS + 模拟
 */
public class MeetingInMaze {
    static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
        int startA = 0;
        int[] patrolPath = {0,1,2,3,4};
        MeetingInMaze m = new MeetingInMaze();
        int meetRounds = m.minMeetRounds(n, edges, startA, patrolPath);
        System.out.println("meetRounds = " + meetRounds);
    }
    /**
     *  邻接表 + BFS + 模拟
     * */
    public int minMeetRounds(int n, int[][] edges, int startA, int[] patrolPath) {
        // 1. 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            if (edge.length == 2) {
                int u = edge[0], v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        // 2. 生成 B 的位置周期序列
        List<Integer> bCycle = new ArrayList<>();
        if (patrolPath.length == 1) {
            bCycle.add(patrolPath[0]);
        } else {
            for (int i = 0; i < patrolPath.length; i++) {
                bCycle.add(patrolPath[i]);
            }
            for (int i = patrolPath.length - 2; i >= 1; i--) {
                bCycle.add(patrolPath[i]);
            }
        }
        int period = bCycle.size();

        // 3. BFS 计算 A 到各房间的最短距离
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        dist[startA] = 0;
        queue.offer(startA);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.get(u)) {
                if (dist[v] == Integer.MAX_VALUE) {
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }

        // 4. 逐回合模拟
        for (int t = 0; t <= 2 * n * period; t++) { // 足够大的上限
            int bPos = bCycle.get(t % period);
            if (dist[bPos] != Integer.MAX_VALUE && dist[bPos] <= t) {
                return t;
            }
        }

        return -1; // 无法相遇
    }
}
