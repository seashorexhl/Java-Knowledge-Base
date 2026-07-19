package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-19 12:56
 * @Description: 200. 岛屿数量 经典连通分量问题
 */
public class numIslands {
    static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        numIslands nis = new numIslands();
        int islands = nis.numIslands(grid);
        System.out.println(islands);
    }
    /**
     * ==================== 方法一：深度优先搜索 (DFS) ====================
     * 核心思想：遍历网格，遇到 '1' 就认为发现了一个新岛屿（计数+1），
     * 然后立刻触发 DFS，把与它相连的所有 '1' 都“沉没”（变成 '0'），
     * 这样在后续遍历中就不会重复计算同一个岛屿了。
     */
    /**
     * DFS 辅助方法：将当前岛屿及其相连的所有陆地“沉没”
     * */

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        // 【递归终止条件】：越界，或者当前格子是水('0')，直接返回
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        // 【核心操作】：将当前陆地沉没，防止后续重复访问（相当于打标记 visited）
        // 向四个方向（上、下、左、右）继续深度搜索

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    /**
     *  DFS 主方法：计算岛屿数量
     * */
    public int numIslands(char[][] grid) {
        // 边界条件：网格为空

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        // 【递归终止条件】：越界，或者当前格子是水('0')，直接返回

        int num_islands = 0; // 岛屿计数器

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }

    /**
     * 方法二：广度优先搜索
     * */
    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        //
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    /**
     * 业务逻辑类：计算岛屿数量
     */
    public int numIslands2(char[][] grid) {
        // 边界条件处理：如果网格为空，直接返回 0

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;// 总行数
        int nc = grid[0].length;// 总列数
        int num_islands = 0;// （注：这个变量在这里其实没用到，最终结果由 uf.getCount() 提供）
        // 1. 初始化并查集，此时 count 已经等于陆地 '1' 的总数

        UnionFind uf = new UnionFind(grid);
        // 2. 遍历网格，将相邻的陆地合并

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                // 如果当前格子是陆地

                if (grid[r][c] == '1') {
                    // 【防重复处理骚操作】：将当前陆地沉没（置为 '0'）
                    // 这样在后续遍历到它的邻居时，就不会再把它当作 '1' 去重复处理

                    grid[r][c] = '0';
                    // 检查四个方向（上下左右），如果相邻也是陆地，就进行合并
                    // 注意：这里检查四个方向是可以的，但配合上面的 grid[r][c] = '0'，
                    // 实际上只会触发“下”和“右”的合并，因为“上”和“左”在之前的遍历中已经被处理过了。

                    // 检查上方
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    // 检查下方

                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    // 检查左方

                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    // 检查右方

                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        // 3. 遍历结束，所有相连的陆地都已合并，直接返回剩下的帮派数量
        return uf.getCount();
    }

    /**
     * 方法三：并查集
     * 核心类：用于管理节点的合并与查询
     */
    class UnionFind {
        int count;// 记录当前独立集合（帮派/岛屿）的数量
        int[] parent; // 记录每个节点的父节点（老大是谁）
        int[] rank;// 记录树的深度（用于按秩合并，防止树退化成链表）
        /**
         * 构造函数：根据二维网格初始化并查集
         */
        public UnionFind(char[][] grid) {
            count = 0;// 初始帮派数量为 0
            int m = grid.length;// 网格的行数
            int n = grid[0].length;// 网格的列数
            // 将二维网格映射为一维数组（总节点数 = 行数 * 列数）

            parent = new int[m * n];

            rank = new int[m * n];
            // 遍历整个二维网格

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    // 核心：只有陆地 '1' 才能成为一个独立的帮派

                    if (grid[i][j] == '1') {
                        // 【二维转一维映射公式】：当前坐标 (i, j) 在一维数组中的下标 = i * 列数 + j
                        // 初始时，每个陆地节点自己认自己当老大

                        parent[i * n + j] = i * n + j;
                        // 每发现一块新陆地，独立的帮派数量就 +1

                        ++count;
                    }
                    // 水 '0' 不参与并查集，其 rank 初始化为 0 即可

                    rank[i * n + j] = 0;
                }
            }
        }
        /**
         * 查找根节点（老大），并使用【路径压缩】
         * 路径压缩：在找老大的过程中，把路上遇到的所有节点都直接指向老大，让树变扁平
         */
        public int find(int i) {
            // 递归查找，并将当前节点直接指向根节点
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }
        /**
         * 合并两个节点（将两个帮派合并为一个）
         */
        public void union(int x, int y) {
            int rootx = find(x);// 找到 x 的老大
            int rooty = find(y);// 找到 y 的老大
            if (rootx != rooty) {
                // 【按秩合并】：把矮的树挂到高的树下面，保持树的平衡
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;// y 的树矮，挂到 x 下面
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;// x 的树矮，挂到 y 下面
                } else {
                    // 两棵树一样高，随便挂，但被挂的树的老大深度要 +1

                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                // 【关键】：成功合并了两个帮派，独立的帮派总数必须 -1

                --count;
            }
        }
        /**
         * 获取当前连通分量（岛屿）的数量
         */
        public int getCount() {
            return count;
        }
    }

}
