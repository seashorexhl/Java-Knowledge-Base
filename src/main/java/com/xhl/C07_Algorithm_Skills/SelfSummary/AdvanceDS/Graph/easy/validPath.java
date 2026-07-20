package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-20 19:10
 * @Description:    1971. 寻找图中是否存在路径
 */
public class validPath {
    static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0,1},{1,2},{2,0}}; // 构成一个 三角形 闭环
        int source = 0;
        int destination = 2;
        com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.easy.validPath vp = new com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.easy.validPath();
        boolean b = vp.validPath(n, edges, source, destination);
        System.out.println("图中是否存在路径?" + b);
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {


        return false;
    }
}
