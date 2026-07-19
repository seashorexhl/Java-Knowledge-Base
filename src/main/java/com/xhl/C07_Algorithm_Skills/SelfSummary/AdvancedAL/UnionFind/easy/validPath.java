package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-08 23:53
 * @Description: 1971. 寻找图中是否存在路径
 * 并查集 Union-Find
 * 无向图中两个节点是否存在 路径
 */
public class validPath {
    // 测试主方法
    static void main() {
        int n = 3;
        int[][] edges = {{0,1},{1,2},{2,0}}; // 构成一个 三角形 闭环
        int source = 0;
        int destination = 2;
        validPath vp = new validPath();
        boolean b = vp.validPath(n, edges, source, destination);
        System.out.println("图中是否存在路径?" + b);

    }
    /**
     *  方法一：BFS 广度优先搜索
     * */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 1. 【建图阶段】使用邻接表构建无向图
        // 创建一个长度为 n 的数组，数组的每个元素是一个 List，用来存储与该节点相连的邻居节点

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 初始化每一个节点的邻居列表，防止后续出现空指针异常 (NullPointerException)
        // 遍历所有的边，将无向图转换为邻接表

        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            // 无向图是双向的：x 的邻居里有 y，y 的邻居里也有 x

            adj[x].add(y);
            adj[y].add(x);
        }
        // 2. 【BFS 初始化阶段】
        // 创建访问记录数组，标记节点是否已经被遍历过，防止图中有环导致死循环

        boolean[] visited = new boolean[n];
        // 创建 BFS 专用的队列（ArrayDeque 性能优于 LinkedList）

        Queue<Integer> queue = new ArrayDeque<>();
        // 将起点加入队列，并标记为已访问

        queue.offer(source);
        visited[source] = true;
        // 3. 【BFS 遍历阶段】
        // 只要队列不为空，就一直向外扩散搜索

        while (!queue.isEmpty()) {
            // 取出队首节点（当前正在处理的节点）

            int vertex = queue.poll();
            // 【提前终止优化】：如果当前节点就是目标节点，说明已经连通，直接跳出循环
            // 这一步可以节省大量不必要的后续遍历时间

            if (vertex == destination) {
                break;
            }
            // 遍历当前节点的所有邻居

            for (int next : adj[vertex]) {
                // 如果邻居节点还没有被访问过

                if (!visited[next]) {
                    // 将邻居加入队列，等待后续处理

                    queue.offer(next);
                    // 立即标记为已访问（这一步必须在入队时做，不能等出队时做，否则会导致重复入队）

                    visited[next] = true;
                }
            }
        }
        // 4. 【结果返回】
        // 如果 destination 被标记为 true，说明从 source 出发能够到达它（两点连通）
        // 如果依然是 false，说明遍历完所有能到达的节点都没遇到 destination（两点不连通）
        return visited[destination];
    }

    /**
     *  方法二：深度优先搜索
     * */
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        // 1. 建图（与 BFS 相同）

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            adj[x].add(y);
            adj[y].add(x);
        }
        boolean[] visited = new boolean[n];
        // 2. 启动 DFS
        return dfs(source, destination, adj, visited);
    }
    /**
     *  DFS  递归核心逻辑
     * */
    public boolean dfs(int source, int destination, List<Integer>[] adj, boolean[] visited) {
        // 【递归终止条件】：如果当前节点就是目标节点，说明找到了路径

        if (source == destination) {
            return true;
        }
        // 标记当前节点为已访问

        visited[source] = true;
        // 遍历当前节点的所有邻居

        for (int next : adj[source]) {
            // 如果邻居没被访问过，并且从该邻居出发能到达终点（递归）

            if (!visited[next] && dfs(next, destination, adj, visited)) {
                return true;// 只要有一条路能通，就立刻返回 true
            }
        }
        // 所有邻居都走不通，说明从当前节点出发无法到达终点，回溯

        return false;
    }

    /**
     *  方法三：并查集
     *  核心思想：将相连的节点合并到同一个集合，最后判断起点和终点是否属于同一个集合
     *   优点：不需要建图遍历，不需要 visited 数组，适合动态加边和多次查询
     * */
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        // 起点和终点相同，直接返回 true
        if (source == destination) {
            return true;
        }
        // 初始化并查集
        UnionFind uf = new UnionFind(n);
        // 遍历所有的边，将相连的节点合并（认同一个老大）

        for (int[] edge : edges) {
            uf.uni(edge[0], edge[1]);
        }
        // 判断起点和终点是否在同一个集合中（老大是不是同一个人）
        return uf.connect(source, destination);
    }
}

    /**
     *  并查集数据结构模板
     *  包含：路径压缩 + 按秩合并
     * */
class UnionFind {
    private int[] parent;// 记录每个节点的父节点
    private int[] rank;// 记录树的深度（用于按秩合并，防止树退化成链表）

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 初始时，每个节点都是自己的老大
        }
    }
    /**
     * 合并两个节点
     */
    public void uni(int x, int y) {
        int rootx = find(x);// 找到 x 的老大
        int rooty = find(y);// 找到 y 的老大
        // 如果老大不是同一个人，才需要合并

        if (rootx != rooty) {
            // 按秩合并：把矮的树挂到高的树下面，保持树的平衡

            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                // 两棵树一样高，随便挂，但被挂的树的老大深度要 +1
                parent[rooty] = rootx;
                rank[rootx]++;
            }
        }
    }
    /**
     * 查找根节点（老大），并使用【路径压缩】
     * 路径压缩：在找老大的过程中，把路上遇到的所有节点都直接指向老大，让树变扁平
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);// 递归查找并压缩路径
        }
        return parent[x];
    }
    /**
     * 判断两个节点是否连通
     */
    public boolean connect(int x, int y) {
        return find(x) == find(y);
    }

}
