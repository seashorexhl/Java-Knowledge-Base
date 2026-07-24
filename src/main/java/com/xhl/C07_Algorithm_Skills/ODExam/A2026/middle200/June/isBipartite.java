package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.June;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:09
 * @Description: 0603-资源隔离分组校验-200分
 *
 */
public class isBipartite {

    static void main() {
         int n = 2;
         List<int[]> list = new ArrayList<>();
        List<int[]> edges1 = new ArrayList<>();
        edges1.add(new int[]{1, 2});
        edges1.add(new int[]{2, 3});
        edges1.add(new int[]{3, 4});
        edges1.add(new int[]{4, 1});

    }

    public static boolean isBipartite(int n, List<int[]> edges) {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (u == v) {
                return false;
            }
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);
        for (int i = 1; i <= n; i++) {
            if (color[i] == -1) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                color[i] = 0;
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : graph.get(u)) {
                        if (color[v] == -1) {
                            color[v] = 1 - color[u];
                            queue.add(v);
                        } else if (color[v] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static int[] canDivide(int[] resourceCount, List<List<int[]>> conflicts) {
        int[] results = new int[resourceCount.length];
        for (int i = 0; i < resourceCount.length; i++) {
            int n = resourceCount[i];
            List<int[]> edges = conflicts.get(i);
            results[i] = isBipartite(n, edges) ? 1 : 0;
        }
        return results;
    }
}
