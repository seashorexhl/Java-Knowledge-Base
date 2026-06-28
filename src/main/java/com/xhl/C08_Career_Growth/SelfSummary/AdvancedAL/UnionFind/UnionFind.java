package com.xhl.Career_Growth.SelfSummary.AdvancedAL.UnionFind;

/**
 * @Author: xhl
 * @Date: 2026-06-15 22:37
 * @Description: 并查集 UnionFind
 */
/**
 * 并查集（Union-Find）数据结构通用实现
 * 支持路径压缩和按秩合并优化
 */

public class UnionFind {
    private int[] parent;  // 父节点数组
    private int[] rank;    // 秩数组（用于按秩合并）
    private int count;     // 连通分量数量

    /**
     * 构造函数
     * @param n 元素总数
     */
    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        rank = new int[n];

        // 初始化：每个元素自成一个集合
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;  // 初始秩为0
        }
    }

    /**
     * 查找元素所在集合的根节点（带路径压缩）
     * @param x 元素索引
     * @return 根节点索引
     */
    public int find(int x) {
        if (parent[x] != x) {
            // 路径压缩：将查找路径上的所有节点直接指向根节点
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 合并两个元素所在的集合
     * @param x 元素1
     * @param y 元素2
     * @return 是否成功合并（如果原本就在同一集合则返回false）
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;  // 已在同一集合中
        }

        // 按秩合并：将秩较小的树合并到秩较大的树下
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // 秩相等时，选择一个作为新根并增加其秩
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        count--;  // 连通分量数量减1
        return true;
    }

    /**
     * 检查两个元素是否连通
     * @param x 元素1
     * @param y 元素2
     * @return 是否在同一集合中
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * 获取当前连通分量数量
     * @return 连通分量数量
     */

    public int getCount() {
        return count;
    }

}
