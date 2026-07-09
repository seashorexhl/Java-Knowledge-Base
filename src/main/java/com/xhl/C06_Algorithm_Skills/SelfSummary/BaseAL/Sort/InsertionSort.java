package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Sort;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-09 00:16
 * @Description: 3.插入排序
 *
 */
public class InsertionSort {
    static void main(String[] args) {
        int[] arr ={3,5,2,4,7,9,1,8,6};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // 将比 key 大的元素向后移
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // 插入到正确位置
        }
    }
}
