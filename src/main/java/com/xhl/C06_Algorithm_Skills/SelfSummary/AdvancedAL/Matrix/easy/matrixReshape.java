package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix.easy;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-07-07 14:29
 * @Description: 566. 重塑矩阵
 */
public class matrixReshape {
    static void main(String[] args) {
        int[][] mat = {{1,2},{3,4}};
        int r = 2;
        int c = 4;
        matrixReshape mr = new matrixReshape();
        int[][] ints = mr.matrixReshape(mat, r, c);
        for(int[] arr : ints){
            System.out.println(Arrays.toString(arr));
        }
    }
    // 相同的 行遍历顺序
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[r][c];
        if (row * col != r * c) {
            return mat;
        }
        int[] temp = new int[r * c];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[(i * col) + j] = mat[i][j];
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = temp[(i * col) + j];
            }
        }
        return  res;
    }
    // 方法一：二维数组的一维表示 单循环坐标映射法
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = nums[x / n][x % n];
        }
        return ans;
    }

}
