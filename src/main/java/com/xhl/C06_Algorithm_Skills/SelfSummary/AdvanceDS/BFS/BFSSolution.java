package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvanceDS.BFS;

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
