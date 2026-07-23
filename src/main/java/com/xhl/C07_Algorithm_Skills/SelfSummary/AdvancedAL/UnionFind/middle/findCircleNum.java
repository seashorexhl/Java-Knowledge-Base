package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-19 15:05
 * @Description: 547. 省份数量
 *  方法一：深度优先搜索 DFS
 *  方法二：广度优先 BFS
 *  方法三：并查集 Union-Find
 */
public class findCircleNum {
    static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        findCircleNum fcn = new findCircleNum();
        int res = fcn.findCircleNum(grid);
        System.out.println(res);
    }

    /**
     * 方法一：深度优先搜索
     */
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                provinces++;
            }
        }
        return provinces;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }
    /**
     * 方法二：广度优先搜索
     * */
    public int findCircleNum1(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < cities; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }
    /**
     * 方法三：并查集
     * 核心思路：将邻接矩阵转化为连通分量问题，通过并查集合并相连节点，最终统计根节点数量”。
     */
    public int findCircleNum2(int[][] isConnected) {
        int cities = isConnected.length;
        // 将每个城市视为独立的省份
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }
        //合并有直接相连的城市
        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        //数一数有多少个“根节点”（老大）
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }
    //合并
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }
    // 路径压缩
    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
