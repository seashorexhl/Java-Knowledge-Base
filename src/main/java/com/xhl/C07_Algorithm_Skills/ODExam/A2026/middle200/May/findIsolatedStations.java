package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:51
 * @Description:    寻找孤立水站-200分
 *
 */
public class findIsolatedStations {

    static void main(String[] args) {
        int n = 5;
        List<Integer> sources =  new ArrayList<>();
        sources.add(1);
        List<List<Integer>> pipes =  new ArrayList<>();

    }
    //
    public List<Integer> findIsolatedStations(int n, List<Integer> sources, List<List<Integer>> pipes) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> pipe : pipes) {
            int u = pipe.get(0);
            int v = pipe.get(1);
            int type = pipe.get(2);
            if (type == 0) {
                graph.get(u).add(v);
            } else {
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int s : sources) {
            visited[s] = true;
            queue.add(s);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        List<Integer> isolated = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                isolated.add(i);
            }
        }

        Collections.sort(isolated);
        return isolated;
    }

}
