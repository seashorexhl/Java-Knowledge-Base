package com.xhl.C06_Algorithm_Skills.LeetCode.Top150.Array;

/**
 * @Author: xhl
 * @Date: 2026-04-12 09:37
 * @Description: 大模型 版本
 */
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (in.hasNext()) {
            int m = in.nextInt();
            int n = in.nextInt();
            double alpha = in.nextDouble();

            double[][] X = new double[m][4];
            double[] col1 = new double[m];
            double[] col2 = new double[m];
            double[] col3 = new double[m];

            for (int i = 0; i < m; i++) {
                X[i][0] = in.nextDouble();
                X[i][1] = in.nextDouble();
                X[i][2] = in.nextDouble();
                X[i][3] = in.nextDouble();
            }

            for (int i = 0; i < m; i++) {
                col1[i] = X[i][0];
                col2[i] = X[i][1];
                col3[i] = X[i][2];
            }

            Arrays.sort(col1);
            Arrays.sort(col2);
            Arrays.sort(col3);

            double min1 = col1[0], max1 = col1[m - 1];
            double min2 = col2[0], max2 = col2[m - 1];
            double min3 = col3[0], max3 = col3[m - 1];

            double range1 = max1 - min1;
            double range2 = max2 - min2;
            double range3 = max3 - min3;

            // 归一化
            for (int i = 0; i < m; i++) {
                if (range1 == 0) X[i][0] = 0;
                else X[i][0] = (X[i][0] - min1) / range1;

                if (range2 == 0) X[i][1] = 0;
                else X[i][1] = (X[i][1] - min2) / range2;

                if (range3 == 0) X[i][2] = 0;
                else X[i][2] = (X[i][2] - min3) / range3;
            }

            // BGD 训练
            double w0 = 0.0, w1 = 0.0, w2 = 0.0, w3 = 0.0;
            double[] predictions = new double[m];

            for (int iter = 0; iter < n; iter++) {
                for (int i = 0; i < m; i++) {
                    predictions[i] = w0 + w1 * X[i][0] + w2 * X[i][1] + w3 * X[i][2];
                }

                double sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0;
                for (int i = 0; i < m; i++) {
                    double error = predictions[i] - X[i][3];
                    sum0 += error;
                    sum1 += error * X[i][0];
                    sum2 += error * X[i][1];
                    sum3 += error * X[i][2];
                }

                w0 -= alpha * (sum0 / m);
                w1 -= alpha * (sum1 / m);
                w2 -= alpha * (sum2 / m);
                w3 -= alpha * (sum3 / m);
            }

            // 权重还原
            if (range1 != 0) w1 = w1 / range1;
            if (range2 != 0) w2 = w2 / range2;
            if (range3 != 0) w3 = w3 / range3;

            w0 = w0 - (w1 * min1 + w2 * min2 + w3 * min3);

            // 【核心修改】使用稳健的 BigDecimal 转换方式
            // 1. 使用 String 构造函数避免 double 的二进制精度问题
            // 2. 在 setScale 时直接指定 HALF_EVEN，不再分两步走
            System.out.println(
                    toBankerString(w0) + " " +
                            toBankerString(w1) + " " +
                            toBankerString(w2) + " " +
                            toBankerString(w3)
            );
        }
        in.close();
    }

    /**
     * 最稳健的银行家舍入法实现
     */
    private static String toBankerString(double val) {
        // 使用 String.valueOf() 将 double 转为字符串，可以去除大部分二进制浮点噪声
        // 然后再构造 BigDecimal
        BigDecimal bd = new BigDecimal(String.valueOf(val));
        // 直接在这里进行舍入，一步到位
        bd = bd.setScale(2, RoundingMode.HALF_EVEN);
        return bd.toString();
    }
}
