package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:18
 * @Description: 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class spiralOrder {
    static void main() {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        spiralOrder so = new spiralOrder();

        System.out.println(so.spiralOrder(matrix).toString());
    }
    //    方法一：模拟 模拟+访问标记

    public List<Integer> spiralOrder(int[][] matrix) {
        // 初始化结果列表
        List<Integer> order = new ArrayList<Integer>();
        // 边界条件检查：如果矩阵为空、行数为0或列数为0，直接返回空列表

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        // 获取矩阵的行数和列数
        int rows = matrix.length, columns = matrix[0].length;
        // 创建访问标记数组，记录每个位置是否已经被加入结果集

        boolean[][] visited = new boolean[rows][columns];
        // 矩阵中元素的总数，作为循环的终止条件

        int total = rows * columns;
        // 当前所在的行和列，从左上角 (0, 0) 开始

        int row = 0, column = 0;
        // 定义四个方向的偏移量，按顺时针顺序：右、下、左、上
        // {0, 1} 表示列+1(向右)，{1, 0} 表示行+1(向下)，以此类推
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // 当前方向索引，初始为0，即向右
        int directionIndex = 0;
        // 遍历矩阵中的所有元素

        for (int i = 0; i < total; i++) {
            // 1. 将当前元素加入结果列表
            order.add(matrix[row][column]);
            // 2. 标记当前位置为已访问

            visited[row][column] = true;
            // 3. 试探下一个位置的坐标

            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            // 4. 判断是否需要转向
            // 如果下一个位置越界（超出矩阵范围）或者已经被访问过，则顺时针转向
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            // 5. 按照当前（可能是刚转向后的）方向，更新当前坐标
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;

    }
    // 方法二：按层模拟
    public List<Integer> spiralOrder1(int[][] matrix) {
        // 初始化结果列表

        List<Integer> order = new ArrayList<Integer>();
        // 边界条件检查：矩阵为空或行/列长度为0时，直接返回空列表

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        // 获取矩阵的行数和列数

        int rows = matrix.length, columns = matrix[0].length;
        // 初始化四个边界指针，分别代表当前遍历区域的左、右、上、下边界

        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        // 当左右边界和上下边界没有交错时，说明还有元素未遍历

        while (left <= right && top <= bottom) {
            // 1. 遍历【上边】：从左到右

            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            // 2. 遍历【右边】：从上到下
            // 注意：起点从 top + 1 开始，因为右上角元素 (top, right) 在第一步已经添加过了
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            // 3. 遍历【下边】和【左边】（防止单行或单列情况下的重复遍历）
            // 只有当剩余区域至少是 2x2 的矩阵时，才需要执行这两步
            if (left < right && top < bottom) {
                // 3a. 遍历【下边】：从右到左
                // 注意：起点从 right - 1 开始，因为右下角元素 (bottom, right) 在第二步已经添加过了
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                // 3b. 遍历【左边】：从下到上
                // 注意：起点从 bottom 开始，终点到 top + 1，因为左下角 (bottom, left) 在上一步已添加，左上角 (top, left) 留给下一圈处理
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            // 4. 边界向内收缩，准备遍历下一圈
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
