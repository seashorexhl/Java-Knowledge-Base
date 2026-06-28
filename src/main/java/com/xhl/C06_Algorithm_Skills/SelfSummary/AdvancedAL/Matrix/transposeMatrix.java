package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-21 06:48
 * @Description: 转置矩阵
 */
public class transposeMatrix {

    static void main() {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        transposeMatrix tm =  new transposeMatrix();
        System.out.println(Arrays.deepToString(matrix));

        System.out.println(Arrays.deepToString(tm.transpose(matrix)));

    }
    // 矩阵的转置
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }



}
