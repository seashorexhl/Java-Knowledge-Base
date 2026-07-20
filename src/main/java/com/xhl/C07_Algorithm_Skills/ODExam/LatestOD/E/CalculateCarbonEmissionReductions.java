package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.E;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-19 16:33
 * @Description: 计算碳排放减少量 CalculateCarbonEmissionReductions
 *  深度优先遍历
 */
public class CalculateCarbonEmissionReductions {
    static void main(String[] args) {
        int[] green = {5, 8, 3, 10};
        int[] carbon = {4, 2, 6, 9};
        int[][] edges = {{0,1},{2,3}};
        CalculateCarbonEmissionReductions cce = new CalculateCarbonEmissionReductions();
        int maxed = cce.maxCarbonReduction(green, carbon, edges);
        System.out.println(maxed);
    }
    /**
     *
     * */
    public int maxCarbonReduction(int[] green, int[] carbon, int[][] edges) {
        int n = green.length;
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int maxReduction = -1; // 初始化为 -1，若没有有效区则返回 -1

        // 遍历所有节点，寻找连通分量
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, graph, visited, component);

                int k = component.size();
                // 有效碳交易区要求区域数 k >= 2
                if (k < 2) {
                    continue;
                }

                int G = 0;
                int C = 0;
                for (int node : component) {
                    G += green[node];
                    C += carbon[node];
                }

                int reduction = Math.max(0, G - C) * k;
                if (reduction > maxReduction) {
                    maxReduction = reduction;
                }
            }
        }

        // 若 M = 0，则 edges 为空，所有 component 大小均为 1，maxReduction 保持 -1，符合要求。
        return maxReduction;
    }
    /**
     *  深度优先遍历
     * */
    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }

}
