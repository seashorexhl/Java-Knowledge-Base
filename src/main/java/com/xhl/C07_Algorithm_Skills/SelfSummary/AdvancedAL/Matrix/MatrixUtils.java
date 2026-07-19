package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Matrix;

/**
 * @Author: xhl
 * @Date: 2026-07-03 21:40
 * @Description: 矩阵操作模板
 *  1. 核心矩阵操作模板 (Java)
 */
import java.util.Arrays;

public class MatrixUtils {

    /**
     *  1. 创建矩阵
     * */
    public static double[][] createMatrix(int rows, int cols) {
        return new double[rows][cols];
    }

    /**
     *  2. 矩阵加法
     * */
    public static double[][] add(double[][] A, double[][] B) {
        checkDimensions(A, B, true); // true 表示检查加法维度
        int rows = A.length, cols = A[0].length;
        double[][] C = createMatrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    /**
     *  3. 矩阵乘法
     * */
    public static double[][] multiply(double[][] A, double[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("矩阵A的列数必须等于矩阵B的行数！");
        }
        int m = A.length, n = B[0].length, p = B.length;
        double[][] C = createMatrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < p; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    /**
     *  4. 矩阵转置
     * */
    public static double[][] transpose(double[][] A) {
        int rows = A.length, cols = A[0].length;
        double[][] T = createMatrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                T[j][i] = A[i][j];
        return T;
    }

    /**
     *  5. 矩阵求逆 (基于高斯-约旦消元法)
     * */
    public static double[][] inverse(double[][] A) {
        int n = A.length;
        if (n != A[0].length) throw new IllegalArgumentException("只有方阵才能求逆！");

        // 构建增广矩阵 [A | I]
        double[][] aug = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, aug[i], 0, n);
            aug[i][n + i] = 1.0;
        }

        // 高斯-约旦消元
        for (int i = 0; i < n; i++) {
            // 寻找主元
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(aug[k][i]) > Math.abs(aug[maxRow][i])) maxRow = k;
            }
            double[] temp = aug[i]; aug[i] = aug[maxRow]; aug[maxRow] = temp;

            if (Math.abs(aug[i][i]) < 1e-10) throw new ArithmeticException("矩阵不可逆（奇异矩阵）！");

            // 将主元化为1
            double pivot = aug[i][i];
            for (int j = 0; j < 2 * n; j++) aug[i][j] /= pivot;

            // 将当前列其他元素化为0
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aug[k][i];
                    for (int j = 0; j < 2 * n; j++) aug[k][j] -= factor * aug[i][j];
                }
            }
        }

        // 提取右侧的逆矩阵
        double[][] inv = createMatrix(n, n);
        for (int i = 0; i < n; i++)
            System.arraycopy(aug[i], n, inv[i], 0, n);
        return inv;
    }

    /**
     * 6. 打印矩阵 (方便调试)
     * */
    public static void print(double[][] M) {
        for (double[] row : M) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // 维度检查辅助方法
    private static void checkDimensions(double[][] A, double[][] B, boolean isAdd) {
        if (isAdd) {
            if (A.length != B.length || A[0].length != B[0].length)
                throw new IllegalArgumentException("加法矩阵维度不匹配！");
        }
    }
}
