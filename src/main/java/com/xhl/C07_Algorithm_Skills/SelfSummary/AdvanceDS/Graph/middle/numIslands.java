package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 13:23
 * @Description: 200. 岛屿数量
 *
 */
public class numIslands {
    static void main() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        numIslands nis = new numIslands();
        int islands = nis.numIslands(grid);
        System.out.println("岛屿的数量:"+islands);
    }
    /**
     * 方法一：深度优先搜索
     */
    public int numIslands(char[][] grid) {

        return 0;
    }
    /**
     * 方法一：广度优先搜索
     */
    public int numIslands1(char[][] grid) {

        return 0;
    }
    /**
     *  方法三：并查集
     * */
    public int numIslands2(char[][] grid) {

        return 0;
    }
}
