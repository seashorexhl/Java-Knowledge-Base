package com.xhl.Career_Growth.SelfSummary.BaseAL.Sort;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-25 10:31
 * @Description: 2.选择排序
 * 每次从未排序区间选出最小（或最大）的元素，放到已排序区间的末尾。
 */
public class selectionSort {
    static void main() {
        int[] arr = {3,5,7,2,6,9};

        selectionSort(arr);
        System.out.println("选择排序：");
        System.out.println(Arrays.toString(arr));
    }
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 将找到的最小值与当前位置交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
