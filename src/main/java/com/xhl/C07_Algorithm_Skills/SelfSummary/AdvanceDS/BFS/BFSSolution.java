package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.BFS;

/**
 * @Author: xhl
 * @Date: 2026-06-10 23:07
 * @Description: BFS 广度优先遍历算法 通用模板
 *  队列 Queue
 */

import java.util.Queue;
import java.util.Set;

/**
 *  1.通常用于 二 叉 树的 遍历
 *  2.图中 最短距离 等
 * */
public class BFSSolution {
    public static void main(String[] args) {


    }
    /**
     *  BFS 模板：
     * */
    /*public static  void bfs(int[] nums, int target){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>(); // 防止走回头路
        queue.offer(startNode);
        visited.add(startNode);
        int steps = 0; // 记录步数或层级

        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前层级的节点数量
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                // 1. 处理当前节点：判断是否到达终点
                if (curr == target) return steps;
                // 2. 扩展：将当前节点的所有未访问邻居加入队列
                for (Node neighbor : curr.getNeighbors()) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            steps++; // 一层遍历完，步数+1
        }
    }*/
    /**
     * 计算 从起点 start 到 终点 target 的最近距离
     * */
    int BFS(Node start, Node Target) {
        Queue<Node> qu = null; // 核心数据结构
        Set<Node> visited = null;// 避免走回头路

        qu.offer(start); // 将起点加入 队列
        visited.add(start); //
        int step = 0; //记录扩散的步数
        while (!qu.isEmpty()) {
            int size = qu.size();
            /*将当前队列中的所有节点 向四周扩散*/
            for (int i = 0; i < size; i++) {
                Node cur = qu.poll();
                /*判断是否 到达终点*/
                if(cur.equals(Target)) {
                    return step;
                }
                /*将 cur的 相邻节点 加入队列*/
                for (Node x:cur.adj()){
                    if(visited.contains(x)){
                        qu.offer(x);
                        visited.add(x);
                    }
                }
            }
            /*划重点：更新步数在这里*/
            step++;
        }
        return step - 1;
    }
}
