package com.xhl.C06_Algorithm_Skills.ODExam.A2026.middle200.Apirl;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-14 17:33
 * @Description: 完善核心代码编程--wifi设备网络规划--200分
 * 现在给定一个mxn（不超过50*50）的网络布局图（墙壁用字符'＃表示，空地用字符表示），
 * 请设计一个算法，计算最少放置多少数量的AP来覆盖
 * 所有空地？如果不能按条件完成覆盖，请返回﹣1
 */
public class minAPCount {
    public  static void main(String[] args) {
        // n*m的网格 # 墙壁 . 空地
        char[][] grid= {{'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'},
                {'.','.','.','#','.','.','.'}
        };
        minAPCount map = new minAPCount();
        int count = map.minAPCount(grid);
        System.out.println("最少放置多少数量的AP来覆盖？ ： " + count);
    }
    /**
     * 计算覆盖所有空地所需的最少 AP (Access Point) 数量
     * 核心算法：DFS 回溯 + 递增枚举
     */
    public int minAPCount(char[][] grid) {
        // 1. 边界条件检查

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        // 2. 收集网格中所有空地('.')的坐标

        List<int[]> dots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    dots.add(new int[]{i, j});
                }
            }
        }
        int numDots = dots.size();
        if (numDots == 0) {
            return 0;
        }
        // 3. 预处理：构建每个候选 AP 位置的覆盖范围映射表

        Map<String, Set<String>> coverageMap = new HashMap<>();
        List<int[]> apPositions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    Set<String> cover = new HashSet<>();
                    // 遍历当前位置的 3x3 邻域（即 AP 的覆盖范围）

                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ni = i + di;
                            int nj = j + dj;
                            // 确保不越界且目标也是空地

                            if (ni >= 0 && ni < n && nj >= 0 && nj < m && grid[ni][nj] == '.') {
                                cover.add(ni + "," + nj);
                            }
                        }
                    }
                    String key = i + "," + j;
                    coverageMap.put(key, cover);
                    apPositions.add(new int[]{i, j});
                }
            }
        }
        // 4. 计算理论上的 AP 数量下界和上界
        // 下界：因为一个 AP 最多覆盖 9 个格子，所以至少需要 (总空地数 + 8) / 9 个 AP

        int minPossible = (numDots + 8) / 9;
        int maxPossible = numDots;
        // 5. 递增枚举 AP 的数量 k，从理论下界开始尝试，一旦 DFS 找到可行解，即为最少数量

        for (int k = minPossible; k <= maxPossible; k++) {
            if (dfs(apPositions, coverageMap, new HashSet<>(), new HashSet<>(), k, numDots)) {
                return k;
            }
        }

        return -1;
    }
    /**
     * 【⚠️ 核心 Bug 警告】检查当前 AP 是否可以放置（是否与已放置的 AP 冲突）
     * 当前逻辑：如果两个 AP 的切比雪夫距离 < 3，则返回 false (不能放)
     * 问题：在求“最少 AP 覆盖”时，AP 之间应该尽可能靠近以产生覆盖重叠，而不是互相排斥！
     * 这个强制距离限制会导致程序无法找到最优解，甚至得出错误答案。
     */
    private boolean canPlace(int[] ap, List<int[]> placed) {
        int i = ap[0], j = ap[1];
        for (int[] p : placed) {
            int pi = p[0], pj = p[1];
            if (Math.max(Math.abs(i - pi), Math.abs(j - pj)) < 3) {
                return false;
            }
        }
        return true;
    }
    /**
     * DFS 回溯搜索：尝试在网格中放置 k 个 AP，看能否覆盖所有 numDots 个空地
     */
    private boolean dfs(List<int[]> apPositions, Map<String, Set<String>> coverageMap,
                        Set<String> placedSet, Set<String> covered, int k, int numDots) {
        // 递归终止条件：如果已经放置了 k 个 AP

        if (placedSet.size() == k) {
            // 检查是否所有空地都被覆盖了

            return covered.size() == numDots;
        }
        // 遍历所有可能的候选 AP 位置

        for (int[] pos : apPositions) {
            String key = pos[0] + "," + pos[1];
            // 剪枝 1：如果该位置已经放置过 AP，跳过

            if (placedSet.contains(key)) {
                continue;
            }
            // 剪枝 2：检查是否与已放置的 AP 冲突（注意：这里调用了有 Bug 的方法）
            List<int[]> placedList = new ArrayList<>();
            for (String p : placedSet) {
                String[] coords = p.split(",");
                placedList.add(new int[]{Integer.parseInt(coords[0]), Integer.parseInt(coords[1])});
            }
            if (!canPlace(pos, placedList)) {
                continue;
            }
            // 剪枝 3：计算当前 AP 能带来的“新增覆盖”
            Set<String> newCover = new HashSet<>(coverageMap.get(key));
            newCover.removeAll(covered);// 剔除已经被覆盖的点
            if (newCover.isEmpty()) {
                continue; // 如果这个 AP 不能覆盖任何新的空地，放置它没有意义，跳过
            }
            // 做出选择：放置当前 AP
            placedSet.add(key);
            covered.addAll(newCover);
            // 递归进入下一层

            if (dfs(apPositions, coverageMap, placedSet, covered, k, numDots)) {
                return true;
            }
            // 撤销选择（回溯）：恢复状态，尝试下一个位置
            covered.removeAll(newCover);
            placedSet.remove(key);
        }

        return false;// 所有候选位置都尝试完毕，未找到解
    }
}
