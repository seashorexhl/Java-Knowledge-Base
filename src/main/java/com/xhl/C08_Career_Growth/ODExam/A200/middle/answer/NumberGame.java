package com.xhl.Career_Growth.ODExam.A200.middle.answer;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-26 11:17
 * @Description: 07 Number Game  数字游戏
 *   多源 BFS 模板、网格遍历、多源起点、连通性判断
 *   初始化 + BFS + 结果 ⭐⭐⭐
 */
public class NumberGame {
    static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        // 读取输入，存入列表中
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            lines.add(line);
        }
        // ===== 网格初始化阶段 =====
        int rows = lines.size(); //行
        int cols = lines.get(0).split(" ").length;// 列
        String[][] grid = new String[rows][cols];//存储网格状态
        Queue<int[]> q = new LinkedList<>();//BFS队列，存储坐标 [行, 列]
        int toConvert = 0;  // 需要改造的区域数量（初始统计所有"NO"）

        // 初始化网格和队列 将输入解析为二维网格，并初始化BFS队列
        for (int r = 0; r < rows; r++) {
            String[] row = lines.get(r).split(" ");
            for (int c = 0; c < cols; c++) {
                grid[r][c] = row[c];
                // 如果是已改造区域（YES），加入BFS队列作为扩散起点
                if (row[c].equals("YES")) {
                    q.add(new int[]{r, c});
                    // 统计需要改造的区域数量（NO）
                } else if (row[c].equals("NO")) {
                    toConvert++;
                }
            }
        }
        // ===== 特殊情况处理 =====
        // 如果没有初始改造点（队列为空），且存在需要改造的区域 → 无法完成
        if (q.isEmpty()) {
            System.out.println(-1);
            return;
        }
        // 如果所有区域已经是YES（toConvert=0）→ 0天即可完成（但题目逻辑通常隐含至少1天，此处按题意处理）

        // ===== BFS核心逻辑 =====
        // 方向数组（上下左右）
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int days = 0;  // 改造天数
        // 当还有可扩散的点 且 仍有未改造区域时继续
        // 广度优先搜索
        while (!q.isEmpty() && toConvert > 0) {
            int size = q.size(); // 当前层级（当天）需要处理的节点数量
            // 一次性处理完当前层级的所有节点（保证天数统计准确）
            for (int i = 0; i < size; i++) {
                int[] pos = q.poll(); //取出 当前坐标
                // 尝试向四个方向扩散
                // 检查新坐标是否合法（不越界）且目标区域是未改造状态（NO）
                for (int[] dir : dirs) {
                    int newRow = pos[0] + dir[0];
                    int newCol = pos[1] + dir[1];

                    // 标记为已改造（防止重复入队）
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol].equals("NO")) {
                        // 标记为已改造（防止重复入队）
                        grid[newRow][newCol] = "YES";
                        // 加入队列等待下一轮扩散
                        q.add(new int[]{newRow, newCol});
                        // 减少剩余待改造数量
                        toConvert--;
                    }
                }
            }
            days++;// 当前层级处理完毕，天数+1
        }
        // ===== 结果判定 =====
        // 如果所有NO都被改造完成 → 输出总天数
        // 如果仍有未改造区域（toConvert > 0）→ 说明存在孤立区域，无法完成
        System.out.println(toConvert == 0 ? days : -1);
    }

}
