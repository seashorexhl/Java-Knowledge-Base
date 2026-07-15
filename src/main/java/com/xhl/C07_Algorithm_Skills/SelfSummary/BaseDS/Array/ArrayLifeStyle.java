package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: xhl
 * @Date: 2026-06-02 18:03
 * @Description: 数组的 基本操作 生命周期 创建、访问、修改、销毁 多维数组
 */
public class ArrayLifeStyle {
    public static void main(String[] args) {
        /*初始化*/
        // 一维数组
        int[] array = new int[5];
        int[] nums = {10,20,30,40,50,60,70,80,90,100};

        // 二维数组
        String[][] arr = {{"a", "b"}, {"c", "d", "e"}}; // 示例二维数组
        String result = Arrays.stream(arr)           // 获取 String[] 的流
                .flatMap(Arrays::stream)   // 将多行 String[] 拍平合并成一个 Stream<String>
                .collect(Collectors.joining(" ")); // 用空格拼接
        System.out.print("result: ");
        System.out.println(result);
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // 方法一：基础 for 循环（可获取索引）
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // 方法二：增强 for 循环（不需要索引时推荐）
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // 使用 Stream 的 flatMap 将二维数组“展平”成一维流，然后逐个打印
        Arrays.stream(matrix).flatMapToInt(Arrays::stream).forEach(v -> System.out.print(v + " "));
        System.out.println("");
        /*修改*/
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1; //赋值
        }

        /*访问*/
        // 1.正常 For循环 遍历
        System.out.println("打印 array 数组中的所有元素：");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");

        }

        // 转字符串
        System.out.println(Arrays.toString(array));

        /*判空*/
        if(array.length == 0){
            return;
        }
        /*销毁*/
            /*在 Java 中,数组的 内存管理 是由 虚拟机 进行的,当一个 数组不再被引用时,它会被自动标记为垃圾,
        JVM 会再适当的时候 收回其占用的内存,因此程序员 不需要手动销毁数组.*/

        /*常用API*/
        Arrays.sort(array);
        Arrays.stream(array).forEach(num -> System.out.print(num + " "));
        Arrays.stream(array).forEach(System.out::println);
        int i = Arrays.binarySearch(array, 8);
        System.out.println("二分查找值为8的下标："+i);

    }
}
