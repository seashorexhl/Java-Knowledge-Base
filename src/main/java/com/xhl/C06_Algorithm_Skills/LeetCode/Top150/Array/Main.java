package com.xhl.Algorithm_Skills.LeetCode.Top150.Array;

/**
 * @Author: xhl
 * @Date: 2026-04-12 09:35
 * @Description: 牛客网 版本
 *
 */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        double a = in.nextDouble();
        double[][] X = new double[m][4];
        double[] x1 = new double[m];
        double[] x2 = new double[m];
        double[] x3 = new double[m];
        // int[] x1 = new int[m];
        for (int i = 0; i < m; i++) {
            X[i][0] = in.nextInt();
            X[i][1] = in.nextInt();
            X[i][2] = in.nextInt();
            X[i][3] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            x1[i] = X[i][0];
            x2[i] = X[i][1];
            x3[i] = X[i][2];
        }
        Arrays.sort(x1);
        Arrays.sort(x2);
        Arrays.sort(x3);
        double w0 = 0.0, w1 = 0.0, w2 = 0.0, w3 = 0.0;
        double[][] minmax = new double[3][2];
        minmax[0][0] = x1[0];
        minmax[0][1] = x1[m - 1];
        minmax[1][0] = x2[0];
        minmax[1][1] = x2[m - 1];
        minmax[2][0] = x3[0];
        minmax[2][1] = x3[m - 1];
        for (int i = 0; i < m; i++) {
            if (minmax[0][1] == minmax[0][0]) {
                X[i][0] = 0;
            } else {
                X[i][0] = (X[i][0] - minmax[0][0]) / (minmax[0][1] - minmax[0][0]);
            }
            if (minmax[1][1] == minmax[1][0]) {
                X[i][1] = 0;
            } else {
                X[i][1] = (X[i][1] - minmax[1][0]) / (minmax[1][1] - minmax[1][0]);
            }
            if (minmax[2][1] == minmax[2][0]) {
                X[i][2] = 0;
            } else {
                X[i][2] = (X[i][2] - minmax[2][0]) / (minmax[2][1] - minmax[2][0]);
            }
        }
        double[] f = new double[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[j] = w0 + w1 * X[j][0] + w2 * X[j][1] + w3 * X[j][2];
            }
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;
            double sum0 = 0;
            for (int j = 0; j < m; j++) {
                sum0 += f[j] - X[j][3];
                sum1 += (f[j] - X[j][3]) * X[j][0];
                sum2 += (f[j] - X[j][3]) * X[j][1];
                sum3 += (f[j] - X[j][3]) * X[j][2];
            }
            double gk0 = sum0 / m;
            double gk1 = sum1 / m;
            double gk2 = sum2 / m;
            double gk3 = sum3 / m;
            w0 = w0 - a * gk0;
            w1 = w1 - a * gk1;
            w2 = w2 - a * gk2;
            w3 = w3 - a * gk3;
        }
        if (minmax[0][1] == minmax[0][0]) {
            w1 = 0;
        } else {
            w1 = w1 / (minmax[0][1] - minmax[0][0]);
        }
        if (minmax[1][1] == minmax[1][0]) {
            w2 = 0;
        } else {
            w2 = w2 / (minmax[1][1] - minmax[1][0]);
        }
        if (minmax[2][1] == minmax[2][0]) {
            w3 = 0;
        } else {
            w3 = w3 / (minmax[2][1] - minmax[2][0]);
        }
        w0 = w0 - (w1 * minmax[0][0] + w2 * minmax[1][0] + w3 * minmax[2][0]);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.printf(df.format(BigDecimal.valueOf(w0)) + " " + df.format(BigDecimal.valueOf(w1)) + " " + df.format(BigDecimal.valueOf(w2)) + " " + df.format(BigDecimal.valueOf(w3)));
    }
}
/**
        描述 小红正在开发一款大型语言模型的推理优化工具。为了能够准确预估模型在不同硬件任务下的耗时情况，她打算构建一个简单的线性回归模型。该模型通过三个关键特征：协议连接数、包转发率和内存占用百分比，来预测最终的资源消耗指标值。
        为了提高模型的训练效率和稳定性，小红决定采用带有数据归一化处理的批量梯度下降法（
        Batch Gradient
        Descent,BGD）来优化模型参数。具体流程如下：
        1.特征归一化：
        对每一列特征分别进行 Min-
        Max 归一化。
        假设某列特征为 x，
        其最小值为 min，
        最大值为 max，
        则归一化后的值 x
′
        =
        (
        x
−
        m i
        n
)
        /
        (
        m a
        x
−
        m i
        n
)
        x
′
        =(x−min)/(max−min)。若该列的最大值与最小值相等，则该列所有归一化后的值直接设为 0。
        2.权重训练：
        初始化偏置项 w0
        以及三个特征对应的权重 w1、w2、
        w3 为 0。
        随后进行 N
        轮迭代，每轮迭代中小红会根据当前的权重计算所有样本的预测值，并以此计算梯度。梯度的计算方式为：
        第 k
        个权重的梯度等于所有样本的“预测值与真实值之差”乘以“
        该样本第 k
        个归一化特征”后的平均值（
        对于 w0，其对应的特征值恒为 1）。所有权重在每一轮结束时同时进行更新：
        w k
←
        w k
−
        α
⋅
        g k
        w k
​
        ←
        w k
​
        −α⋅
        g k
​
        ，
        其中 α
        α 为学习率，
        g_k 为梯度。
        3.权重还原：训练完成后，需要将归一化空间下的权重还原回原始数据的量纲。
        特征权重还原公式为 w
        j
′
        =
        w j
/
        (
        m a
        x j
−
        m i
        n j
)
        w j
′
        ​
        =
        w j
​
        /(
        max j
​
        −
        min j
​
        )（
        若 max = min，则还原权重为 0）。还原后的偏置项公式为：
        w
0
        ′
        =
        w
0
        −
        ∑
        j
=
        1 3
        (
        w j
′
        ⋅
        m i
        n j
)
        w
0
        ′
        ​
        =w
0
        ​
        −∑
        j=1 3
        ​
        (
        w j
′
        ​
        ⋅
        min j
​
        )。
        请你帮助小红完成这个训练过程，并输出还原后的最终参数。
        输入描述：
        第一行输入一个整数 m（1 ≤m ≤ 10000），表示训练样本的数量。
        第二行输入一个整数 N（1 ≤N ≤ 1000），表示梯度下降的迭代次数。
        第三行输入一个浮点数 α
        α（0.00 ≤
        α α ≤ 1.00），表示学习率。
        接下来的 m
        行，每行包含 4
        个整数 x1, x2, x3, y。
        其中 x1、x2、
        x3 分别为三个特征值（0 ≤x1 ≤ 1000,0 ≤x2 ≤ 10000,0 ≤x3 ≤ 100），
        y 为资源消耗的真实观测值（0 ≤y ≤ 10000）。
        输出描述：
        输出一行，包含 4个浮点数，
        分别代表还原后的 w0, w1, w2, w3。结果需使用银行家舍入法（即四舍六入五成双：保留位后一位小于 5则舍去，大于 5则进位，等于 5且后面无其他非零数时看前一位，前一位为偶数则舍去，奇数则进位）保留 2位小数，数值之间用空格隔开。
        示例1 输入：
        2 1 0.10 10 100 5 50 20 300 15 100
        复制 输出：
        -2.500.500.020.50
        复制 说明：

        在本样例中，第一列特征的范围是 [10,20]，第二列是 [100,300]，第三列是 [5,15]。

        归一化后，
        归一化权重 w0，真实值为 50；
        w3 均为，真实值为 100。

        初始权重均为 0，经过 1轮迭代更新后，
        得到 w
        为 7.5，w1,w2,

        第一个样本的特征为(0,0,0) 5.0。

        最后进行量纲还原，

        第二个样本特征为(1,1,1)'1 = 0.5, w'2=0.025,w'3 = 0.5, w'0=-2.5。

        按照银行家舍入法，0.025舍入两位小数为 0.02（因为 2是偶数），故输出结果如上。
 */