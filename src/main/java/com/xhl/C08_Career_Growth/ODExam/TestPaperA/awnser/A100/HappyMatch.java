package com.xhl.C08_Career_Growth.ODExam.TestPaperA.awnser.A100;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-06 02:20
 * @Description: 02 Happy Match  开心消消乐
 */
public class HappyMatch {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt(); // 输入矩阵的行数
        int cols = in.nextInt(); // 输入矩阵的列数
        int[][] matrix = new int[rows][cols]; // 定义一个rows行cols列的矩阵
        for (int i = 0; i < rows; i++) { // 遍历矩阵的每一行
            for (int j = 0; j < cols; j++) { // 遍历矩阵的每一列
                matrix[i][j] = in.nextInt(); // 读入矩阵的每一个元素
            }
        }

        int result = 0; // 定义结果变量，表示矩阵中1的连通块数量
        for (int i = 0; i < rows; i++) { // 遍历矩阵的每一行
            for (int j = 0; j < cols; j++) { // 遍历矩阵的每一列
                // 从任意一个位置的1开始遍历
                if (matrix[i][j] == 1) { // 如果当前位置是1
                    result++; // 连通块数量加1
                    dfs(matrix, i, j); // 对以当前位置为起点的连通块进行深度优先遍历
                }
            }
        }
        System.out.println(result); // 输出矩阵中1的连通块数量
    }

    public static void dfs(int[][] matrix, int x, int y) {
        matrix[x][y] = 0; // 将当前位置的值设为0，表示已经遍历过
        int rows = matrix.length; // 矩阵的行数
        int cols = matrix[0].length; // 矩阵的列数
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // 定义8个方向的偏移量
        for (int[] dir : directions) { // 遍历8个方向
            int nextX = x + dir[0]; // 计算下一个位置的行坐标
            int nextY = y + dir[1]; // 计算下一个位置的列坐标
            if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && matrix[nextX][nextY] == 1) { // 如果下一个位置在矩阵范围内且值为1
                dfs(matrix, nextX, nextY); // 对下一个位置进行深度优先遍历
            }
        }
    }
}
