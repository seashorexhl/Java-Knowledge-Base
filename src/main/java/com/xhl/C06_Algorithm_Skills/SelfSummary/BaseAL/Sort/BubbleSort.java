package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Sort;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-08 05:40
 * @Description: 1.冒泡排序
 *  相邻元素两两比较，将较大的元素像气泡一样“浮”到数组末尾。每一轮确定一个最大值。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 111, 22, 33, 44, 555 };
        BubbleSort bs = new BubbleSort();
        bs.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // 冒泡排序
    public void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            boolean swaped = false;
            for (int j = 0;j<n-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] =temp;
                    swaped = true;
                }
            }
            if(!swaped) break;;
        }
    }

}
