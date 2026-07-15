package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-10 21:56
 * @Description: 图的 邻接 矩阵法
 * 1.网格类（二维矩阵）遍历模板
 * 2.离散节点类（邻接表）遍历模板
 */
/**
 * 核心考点与避坑指南
 *  防死循环（重中之重）：无论是 DFS 还是 BFS，进入新节点时必须第一时间进行标记（加入 visited
 *  集合或修改原矩阵值）。如果不标记，节点之间会互相递归导致栈溢出（Stack Overflow）或死循环。
 * BFS 的层级控制：在求解“最少天数”、“最短距离”的题目时，务必在 while 循环内先记录 size =
 * queue.size()，再用 for 循环处理完这一层的所有节点后再让步数 step++。
 * DFS 的栈溢出风险：当网格规模极大（如
 * 50
 * ×
 * 50
 * 50×50 甚至更大）时，系统默认的递归深度可能不够，极易抛出异常。如果数据量大，建议将 DFS 改为手动
 * 维护栈（Stack）的迭代写法，或者直接使用 BFS。
 * 多源 BFS 技巧：如果是“多个起点同时开始扩散”（如宜居星球改造计划），只需在初始化时将所有起点的坐标
 * 一次性加入队列即可，后续流程与普通 BFS 完全一致。
 * */
public class dfsOnMatrix {
    // 定义四个方向的偏移量：上、下、左、右
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * DFS 递归模板 (常用于连通性判断、寻找所有路径)
     */
    public void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        // 1. 边界检查与状态校验（越界、已访问、或遇到障碍物直接返回）
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
                || visited[x][y] || grid[x][y] == 0) {
            return;
        }

        // 2. 标记当前节点为已访问（防止重复走回头路）
        visited[x][y] = true;

        // 3. 处理当前节点的逻辑（例如：累加面积、收集坐标等）
        // processLogic(x, y);

        // 4. 向四个方向递归深入
        for (int[] dir : dirs) {
            dfs(grid, visited, x + dir[0], y + dir[1]);
        }
    }

    /**
     * BFS 队列模板 (常用于求最短步数、层序遍历、多源扩散)
     */
    public void bfs(int[][] grid, boolean[][] visited, int startX, int startY) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        int step = 0; // 记录天数/步数

        while (!queue.isEmpty()) {
            int size = queue.size(); // 获取当前层的节点数量
            // 逐层处理（如果需要按层统计天数/步数，必须这样写）
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];

                // 处理当前节点逻辑

                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length
                            && !visited[nx][ny] && grid[nx][ny] != 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            step++; // 当前层处理完毕，步数+1
        }
    }
    /**
     * 基于邻接表的 BFS (常用于拓扑排序、二分图检测、孤立点判定)
     */
    public void bfsOnAdjList(Map<Integer, List<Integer>> adjList, int startNode) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            // 处理当前节点
            System.out.println("Visited: " + node);

            // 遍历所有邻居
            for (int neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }
}
