package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-19 15:05
 * @Description: 547. 省份数量
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


        return 0;
    }
    /**
     * 方法二：广度优先搜索
     * */
    public int findCircleNum1(int[][] isConnected) {

        return 0;
    }

    /**
     * 方法三：并查集
     *
     */
    public int findCircleNum2(int[][] isConnected) {
        int cities = isConnected.length;
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
