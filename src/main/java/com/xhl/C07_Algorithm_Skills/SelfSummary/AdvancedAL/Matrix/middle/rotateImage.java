package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:18
 * @Description: 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 */
public class rotateImage {
    static void main() {
       int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
       rotateImage ri = new rotateImage();
       ri.rotate(matrix);
       System.out.println("顺时针旋转 90 度 的 矩阵数据为： ");
       for(int i = 0; i < matrix.length; i++) {
           for(int j = 0; j < matrix[i].length; j++) {
               System.out.print(matrix[i][j] + " ");
           }
           System.out.println(" ");
       }
    }
    /**
     *  方法一：使用辅助数组
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }

    }
    /**
     *  方法二：原地旋转
     * */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
    /**
     *  方法三：用翻转代替旋转
     * */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
