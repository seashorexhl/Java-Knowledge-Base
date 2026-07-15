package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-11 18:35
 * @Description: 二维动态规划
 */
public class DDDP {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        /*普通 for 循环 遍历*/
        for (int i = 0; i < matrix.length; i++) {           // 外层循环遍历“行”
            for (int j = 0; j < matrix[i].length; j++) {    // 内层循环遍历当前行的“列”
                System.out.print(matrix[i][j] + " ");
                // matrix[i][j] = 0; // 可以在这里修改元素的值
            }
            System.out.println(); // 换行
        }

        /* 增强 for-each 循环 遍历*/
        for (int[] row : matrix) {       // 每次取出一个一维数组（一行）
            for (int num : row) {        // 遍历这一行中的每一个元素
                System.out.print(num + " ");
            }
            System.out.println();
        }
        /* Stream 流式遍历（最函数式） */
        //在遍历过程中进行链式的数据过滤、映射或转换操作。

        Arrays.stream(matrix)
                .forEach(row -> {
                    Arrays.stream(row)
                            .forEach(num -> System.out.print(num + " "));
                    System.out.println();
                });


    }

}
